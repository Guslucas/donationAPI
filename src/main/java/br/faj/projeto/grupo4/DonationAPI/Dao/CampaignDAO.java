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

    public Campaign inserir(Campaign c) {
        String type = c.getType();
        if (type.equals("MONEY")){
            MoneyCampaign mc = (MoneyCampaign) c;
            String sqlInsert = "INSERT INTO Campaign (ID, NAME, DESCRIPTION, STARTDATE, ENDDATE, GOAL)"
                    + "VALUES (?, ?, ?, ?, ?, ?,)";
            try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setLong(1, mc.getId());
                preparedStatement.setString(2, mc.getName());
                preparedStatement.setString(3, mc.getDescription());
                preparedStatement.setDate(4, (Date) mc.getStartDate());
                preparedStatement.setDate(5, (Date) mc.getEndDate());
                preparedStatement.setFloat(6, mc.getGoal());
                int result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return mc;
        }
        else if (type.equals("PRODUCT")){
            ProductCampaign pc = (ProductCampaign) c;
            String sqlInsert = "INSERT INTO Campaign (ID, NAME, DESCRIPTION, STARTDATE, ENDDATE, OBJECTIVES)"
                    + "VALUES (?, ?, ?, ?, ?, ?,)";
            try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setLong(1, pc.getId());
                preparedStatement.setString(2, pc.getName());
                preparedStatement.setString(3, pc.getDescription());
                preparedStatement.setDate(4, (Date) pc.getStartDate());
                preparedStatement.setDate(5, (Date) pc.getEndDate());
                Objective[] objective = (Objective[]) pc.getObjectives().toArray();
                preparedStatement.setArray(6, (null));
                int result = preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return pc;
        }
        return null;
    }

    public List<Campaign> getCampaigns() {
        String query = "SELECT * FROM Campaign";
        List<Campaign> campaignList = new ArrayList<>();
        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                String type =  rs.getString("TYPE");
                Date startDate = rs.getDate("STARTDATE");
                Date endDate = rs.getDate("ENDDATE");
                if (type.equals("MONEY")){
                    float goal = rs.getFloat("GOAL");
                    MoneyCampaign mc = new MoneyCampaign(id, name, description, type, startDate, endDate, goal);
                    campaignList.add(mc);
                } else if (type.equals("PRODUCT")){
                    String objectivesQuery = "SELECT * FROM Objective";
                    List<Objective> objectiveList = new ArrayList<>();

                    /*try (Connection connection2 = jdbcTemplate.getDataSource().getConnection()){
                        Statement stmt2 = connection2.createStatement();
                        ResultSet rs2 = stmt2.executeQuery(objectivesQuery);
                        while (rs2.next()){
                            long objectiveId = rs2.getLong("ID");
                            int objectiveQuantity = rs2.getInt("QUANTITY");
                            pc.getObjectives();
                        }
                    } */
                    ProductCampaign pc = new ProductCampaign(id, name, description, type, startDate, endDate, objectiveList);
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

    public void deleteCampaign(Campaign c){
        String deleteSql = "DELETE FROM MONEYCAMPAIGN WHERE ID = ?";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.setLong(1, c.getId());

            int result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}