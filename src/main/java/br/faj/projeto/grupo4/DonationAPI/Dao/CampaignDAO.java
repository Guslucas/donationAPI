package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CampaignDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Campaign inserirCampanha(Campaign c) throws Exception {
        String type = c.getType();
        String sqlInsert = "INSERT INTO Campaign (NAME, DESCRIPTION, START, END, TYPE, MONETARY_GOAL)"
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, c.getName());
            preparedStatement.setString(2, c.getDescription());
            preparedStatement.setDate(3, (Date) c.getStartDate());
            preparedStatement.setDate(4, (Date) c.getEndDate());
            preparedStatement.setString(5, String.valueOf(c.getType().charAt(0)));
            if (c instanceof MoneyCampaign) {
                preparedStatement.setFloat(6, ((MoneyCampaign) c).getGoal());
            }
            else {
                preparedStatement.setNull(6, Types.NUMERIC);
            }
            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                long id = generatedKeys.getLong(1);
                c.setId(id);
                generatedKeys.close();

                if (c instanceof ProductCampaign){
                    List<Objective> objectiveList = ((ProductCampaign) c).getObjectives();

                    for (Objective o : objectiveList) {
                        String insertObjective = "INSERT INTO OBJECTIVE (QUANTITY, ID_PRODUCT, ID_CAMPAIGN)" +
                                "VALUES (?, ?, ?)";

                        PreparedStatement preparedStatement1 = null;

                        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
                            preparedStatement1 = connection.prepareStatement(insertObjective, Statement.RETURN_GENERATED_KEYS);

                            preparedStatement1.setInt(1, o.getQuantity());
                            preparedStatement1.setLong(2, o.getProduct().getId());
                            preparedStatement1.setLong(3, c.getId());

                            int result1 = preparedStatement1.executeUpdate();
                        }
                    }
                }
                return c;
            }
        }
        throw new Exception("Erro");
    }

    public List<Campaign> getCampaigns() throws Exception {
        String query = "SELECT * FROM (\n"+
                "SELECT\n" +
                "C.*,\n" +
                "(SELECT COUNT(*) FROM DONATION D\n" +
                "JOIN Item I ON I.ID_DONATION = D.ID_DONATION\n" +
                "JOIN Product P ON P.ID_Product = I.ID_Product\n" +
                "WHERE D.Type = 'P' AND D.ID_CAMPAIGN = C.ID_CAMPAIGN)/(SELECT SUM(O.Quantity) FROM\n" +
                "Campaign C1\n" +
                "JOIN Objective O ON O.ID_Campaign = C1.ID_Campaign\n" +
                "JOIN Product P ON P.ID_Product = O.ID_Product\n" +
                "WHERE C1.Type='P' AND C1.ID_CAMPAIGN = C.ID_CAMPAIGN) * 100 AS PERCENTAGE\n" +
                "FROM Campaign C WHERE C.Type = 'P' AND END >= SYSDATE\n" +
                "UNION ALL\n" +
                "SELECT C.*,\n" +
                "(SELECT NVL((SUM(D.Monetary_Value)/ C.Monetary_Goal) * 100, 0) FROM Donation D WHERE D.TYPE='M' AND D.ID_Campaign = C.ID_Campaign) AS PERCENTAGE\n" +
                "FROM Campaign C WHERE C.Type='M' AND END >= SYSDATE" +
                ") ORDER BY END";

        List<Campaign> campaignList = new ArrayList<>();

        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ID_CAMPAIGN");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String type = rs.getString("TYPE");
                Date startDate = rs.getDate("START");
                Date endDate = rs.getDate("END");
                float percentage = rs.getLong("PERCENTAGE");

                if (type.equals("M")) {
                    MoneyCampaign mc = new MoneyCampaign(id, name, description, startDate, endDate, percentage);
                    campaignList.add(mc);
                } else if (type.equals("P")) {
                    ProductCampaign pc = new ProductCampaign(id, name, description, startDate, endDate, percentage);
                    campaignList.add(pc);
                }
            }

            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campaignList;
    }

    public List<Product> getProductFromCampaign(int id) throws Exception {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT P.* FROM\n" +
                "CAMPAIGN C\n" +
                "JOIN OBJECTIVE O ON O.ID_CAMPAIGN = C.ID_CAMPAIGN\n" +
                "JOIN PRODUCT P ON P.ID_PRODUCT = O.ID_PRODUCT\n" +
                "WHERE C.ID_CAMPAIGN = ? AND C.TYPE = 'P';\n";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long productID = rs.getLong("ID_PRODUCT");
                String type = rs.getString("TYPE");
                String name = rs.getString("NAME");

                Product p = new Product(productID, type, name);
                productList.add(p);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}