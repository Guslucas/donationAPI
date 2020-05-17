package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Campaign;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import br.faj.projeto.grupo4.DonationAPI.Objective;
import br.faj.projeto.grupo4.DonationAPI.ProductCampaign;
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

    public MoneyCampaign inserir(MoneyCampaign c) {
        String sqlInsert = "INSERT INTO Campaign (ID, NAME, DESCRIPTION, STARTDATE, ENDDATE, GOAL)"
                + "VALUES ('" + c.getId() + "','" + c.getName() + "','" + c.getDescription() + "','" + c.getStartDate()
                + "','" + c.getEndDate() + "','" + c.getGoal() + "')";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            Statement stmt = connection.createStatement();
            int result = stmt.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List<Campaign> getCampaigns() {
        String query = "SELECT * FROM Campaign";

        List<Campaign> campaignList = new ArrayList<>();
        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
            Statement stmt = connection1.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                long id = rs.getLong("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                Date startDate = rs.getDate("STARTDATE");
                Date endDate = rs.getDate("ENDDATE");
                String type = rs.getString("TYPE");
                if (type.equals("M")){
                    float goal = rs.getFloat("GOAL");
                    MoneyCampaign mc = new MoneyCampaign(id, name, description, startDate, endDate, goal);
                    campaignList.add(mc);
                } else if (type.equals("P")){
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
                    ProductCampaign pc = new ProductCampaign(id, name, description, startDate, endDate, objectiveList);
                    campaignList.add(pc);
                }

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campaignList;
    }
}