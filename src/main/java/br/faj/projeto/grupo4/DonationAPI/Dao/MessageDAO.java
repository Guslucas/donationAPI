package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Message;
import br.faj.projeto.grupo4.DonationAPI.Person;
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

    public List<Message> getMessages(long senderId, Donator receiver) {
        String selectReceiver = "SELECT * FROM DONATOR WHERE EMAIL = ?";
        String messageQuery = "SELECT * FROM MESSAGE " +
                "WHERE (ID_SENDER = ? AND ID_RECIPIENT = ?) " +
                "OR (ID_SENDER = ? AND ID_RECIPIENT = ?)" +
                "ORDER BY DATE";

        List<Message> messageList = new ArrayList<>();

        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            preparedStatement1 = connection.prepareStatement(selectReceiver);
            preparedStatement1.setString(1, receiver.getEmail());
            ResultSet rs1 = preparedStatement1.executeQuery();

            while (rs1.next()) {
                long receiverId = rs1.getLong("ID_DONATOR");

                preparedStatement2 = connection.prepareStatement(messageQuery, Statement.RETURN_GENERATED_KEYS);
                preparedStatement2.setLong(1, senderId);
                preparedStatement2.setLong(2, receiverId);
                preparedStatement2.setLong(3, receiverId);
                preparedStatement2.setLong(4, senderId);

                ResultSet rs2 = preparedStatement2.executeQuery();

                while (rs2.next()) {
                    long messageId = rs2.getLong("ID_MESSAGE");
                    Date date = rs2.getTimestamp("DATE");
                    String content = rs2.getString("CONTENT");
                    long messageSenderId = rs2.getLong("ID_SENDER");
                    long messageReceiverId = rs2.getLong("ID_RECIPIENT");

                    Message message = new Message(messageId, content, date, (new Person(messageSenderId)), (new Person(messageReceiverId)));

                    messageList.add(message);
                }
                rs2.close();
            }
            rs1.close();
            preparedStatement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }

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
                rs.close();
            }
            preparedStatement1.close();
            preparedStatement2.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
}
