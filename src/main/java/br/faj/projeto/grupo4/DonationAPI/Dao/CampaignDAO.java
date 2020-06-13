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

//    public Campaign inserir(Campaign c) {
//        String type = c.getType();
//        if (type.equals("M")){
//            MoneyCampaign mc = (MoneyCampaign) c;
//            String sqlInsert = "INSERT INTO Campaign (ID, NAME, DESCRIPTION, STARTDATE, ENDDATE, GOAL)"
//                    + "VALUES (?, ?, ?, ?, ?, ?,)";
//            try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
//                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//                preparedStatement.setLong(1, mc.getId());
//                preparedStatement.setString(2, mc.getName());
//                preparedStatement.setString(3, mc.getDescription());
//                preparedStatement.setDate(4, (Date) mc.getStartDate());
//                preparedStatement.setDate(5, (Date) mc.getEndDate());
//                int result = preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return mc;
//        }
//        else if (type.equals("P")){
//            ProductCampaign pc = (ProductCampaign) c;
//            String sqlInsert = "INSERT INTO Campaign (ID, NAME, DESCRIPTION, STARTDATE, ENDDATE, OBJECTIVES)"
//                    + "VALUES (?, ?, ?, ?, ?, ?,)";
//            try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
//                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
//                preparedStatement.setLong(1, pc.getId());
//                preparedStatement.setString(2, pc.getName());
//                preparedStatement.setString(3, pc.getDescription());
//                preparedStatement.setDate(4, (Date) pc.getStartDate());
//                preparedStatement.setDate(5, (Date) pc.getEndDate());
//                preparedStatement.setArray(6, (null));
//                int result = preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            return pc;
//        }
//        return null;
//    }

    public List<Campaign> getCampaigns() throws Exception {
        String query = "SELECT\n" +
                "C.*,\n" +
                "(SELECT COUNT(*) FROM DONATION D\n" +
                "JOIN Item I ON I.ID_DONATION = D.ID_DONATION\n" +
                "JOIN Product P ON P.ID_Product = I.ID_Product\n" +
                "WHERE D.Type = 'P')/(SELECT SUM(O.Quantity) FROM\n" +
                "Campaign C1\n" +
                "JOIN Objective O ON O.ID_Campaign = C1.ID_Campaign\n" +
                "JOIN Product P ON P.ID_Product = O.ID_Product\n" +
                "WHERE C1.Type='P') * 100 AS PERCENTAGE\n" +
                "FROM Campaign C WHERE C.Type = 'P' AND END >= SYSDATE\n" +
                "UNION ALL\n" +
                "SELECT C.*,\n" +
                "(SELECT NVL((SUM(D.Monetary_Value)/ C.Monetary_Goal) * 100, 0) FROM Donation D WHERE D.TYPE='M' AND D.ID_Campaign = C.ID_Campaign) AS PERCENTAGE\n" +
                "FROM Campaign C WHERE C.Type='M' AND END >= SYSDATE";

        List<Campaign> campaignList = new ArrayList<>();

        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ID_CAMPAIGN");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String type =  rs.getString("TYPE");
                Date startDate = rs.getDate("START");
                Date endDate = rs.getDate("END");
                float percentage = rs.getLong("PERCENTAGE");

                if (type.equals("M")){
                   // float goal = rs.getFloat("GOAL");
                    MoneyCampaign mc = new MoneyCampaign(id, name, description, startDate, endDate, percentage);
                    campaignList.add(mc);
                } else if (type.equals("P")){
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

    public List<Product> getProductFromCampaign(int id) throws Exception{
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

                Product p = new Product(id, type, name);
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