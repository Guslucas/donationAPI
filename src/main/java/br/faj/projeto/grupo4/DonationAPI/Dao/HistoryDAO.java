package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.HistoryItem;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import br.faj.projeto.grupo4.DonationAPI.ProductCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HistoryDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<HistoryItem> getHistory(long id) throws Exception{
        String query = "SELECT D2.DATE, D2.TYPE FROM DONATION D2\n" +
                "JOIN DONATOR D ON D.ID_DONATOR = D2.ID_DONATOR\n" +
                "WHERE D.ID_DONATOR = ?" +
                "ORDER BY D2.DATE DESC";

        List<HistoryItem> historyList = new ArrayList<>();
        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Timestamp date = rs.getTimestamp("DATE");
                String type = rs.getString("TYPE");

                HistoryItem historyItem = new HistoryItem(date, type);
                historyList.add(historyItem);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
}
