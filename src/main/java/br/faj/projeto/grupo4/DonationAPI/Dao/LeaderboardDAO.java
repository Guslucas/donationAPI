package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.LeaderboardItem;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import br.faj.projeto.grupo4.DonationAPI.ProductCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeaderboardDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<LeaderboardItem> getLeaderboard (){
        String query = "SELECT TOP 5 * FROM\n" +
                "(\n" +
                "SELECT D.ID_DONATOR, D.NAME || ' ' || D.SURNAME AS NAME, COUNT(ID_DONATION) AS QTD_DONATION FROM DONATOR D\n" +
                "LEFT JOIN DONATION D2 ON D2.ID_DONATOR = D.ID_DONATOR AND D2.DATE >= SYSDATE - 30\n" +
                "WHERE D.TYPE = 'P'\n" +
                "GROUP BY D.ID_DONATOR\n" +
                "UNION ALL\n" +
                "SELECT D.ID_DONATOR, D.TRADING_NAME AS NAME, COUNT(ID_DONATION) AS QTD_DONATION FROM DONATOR D\n" +
                "LEFT JOIN DONATION D2 ON D2.ID_DONATOR = D.ID_DONATOR\n" +
                "WHERE D.TYPE = 'C' AND D2.DATE >= SYSDATE - 30\n" +
                "GROUP BY D.ID_DONATOR, D.TRADING_NAME\n" +
                ")\n" +
                "ORDER BY QTD_DONATION DESC, ID_DONATOR";

        List<LeaderboardItem> leaderboardItemList = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long donatorId = rs.getLong("ID_DONATOR");
                String name = rs.getString("NAME");
                int quantitydDonation = rs.getInt("QTD_DONATION");

                LeaderboardItem leaderboardItem = new LeaderboardItem(donatorId, name, quantitydDonation);
                leaderboardItemList.add(leaderboardItem);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboardItemList;
    }
}
