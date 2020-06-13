package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Message;
import br.faj.projeto.grupo4.DonationAPI.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MessageDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Message> getMessages(long senderId){
        String query = "SELECT * FROM MESSAGE WHERE ID_SENDER = ?";

        List<Message> messages = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, senderId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long messageId = rs.getLong("ID_MESSAGE");
                Date date = rs.getDate("DATE");
                String content = rs.getString("CONTENT");
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }






    public Message sendMessage(Message message) throws Exception {
        String query = "INSERT INTO MESSAGE (ID_MESSAGE, ID_SENDER, ID_RECIPIENT, DATE, CONTENT)" +
                        "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            preparedStatement.setLong(2, );
//            preparedStatement.setLong(3, );
            preparedStatement.setDate(4, (java.sql.Date) new Date (message.getDate().getTime()));
            preparedStatement.setString(5, message.getContent());

            int result = preparedStatement.executeUpdate();
            if (result == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                long id = generatedKeys.getLong(1);
                message.setId(id);
                generatedKeys.close();
                return message;
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

}
