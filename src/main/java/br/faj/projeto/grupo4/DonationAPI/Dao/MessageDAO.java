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

//    public List<Message> getMessages(long senderId){
//        String messageQuery = "SELECT * FROM MESSAGE WHERE ID_SENDER = ?";
//
//        Message message = new Message();
//        List<Message> messageList = new ArrayList<>();
//
//        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(messageQuery);
//            preparedStatement.setLong(1, senderId);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                long messageId = rs.getLong("ID_MESSAGE");
//                //long sender = rs.getLong("ID_SENDER");
//                long receiverId = rs.getLong("ID_RECIPIENT");
//                Date date = rs.getDate("DATE");
//                String content = rs.getString("CONTENT");
//
//                message.setId(messageId);
//                message.setSender();
////                message.setReceiver(receiverId);
//                message.setDate(date);
//                message.setContent(content);
//
//                for (int i = 0; i < messageList.size(); i++) {
//                    getMessages(messageList.get(i));
//                }
//            }
//            rs.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return messageList;
//    }

    public Message sendMessage(Message message, long senderId) throws Exception {
        String selectReceiver = "SELECT * FROM DONATOR WHERE EMAIL = ?";
        String insertMessage = "INSERT INTO MESSAGE (ID_SENDER, ID_RECIPIENT, DATE, CONTENT)" +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            preparedStatement1 = connection.prepareStatement(selectReceiver);
            preparedStatement1.setString(1, message.getReceiver().getEmail());
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {
                long receiverId = rs.getLong("ID_DONATOR");

                preparedStatement2 = connection.prepareStatement(insertMessage, Statement.RETURN_GENERATED_KEYS);
                preparedStatement2.setLong(1, senderId);
                preparedStatement2.setLong(2, receiverId);
                preparedStatement2.setTimestamp(3, new Timestamp(message.getDate().getTime()));
                preparedStatement2.setString(4, message.getContent());

                int result = preparedStatement2.executeUpdate();
                if (result == 1) {
                    ResultSet generatedKeys = preparedStatement2.getGeneratedKeys();
                    generatedKeys.next();
                    long messageId = generatedKeys.getLong(1);
                    message.setId(messageId);
                    generatedKeys.close();
                }
            }
            preparedStatement1.close();
            preparedStatement2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
}
