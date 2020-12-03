package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.*;
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

    public List<Message> getSenderMessages(long senderId) throws Exception {
        String messageQuery = "SELECT * FROM MESSAGE " +
                "WHERE (ID_SENDER = ?) " +
                "ORDER BY DATE";

        List<Message> messageList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
            ps = con.prepareStatement(messageQuery);
            ps.setLong(1, senderId);

            rs = ps.executeQuery();

            while (rs.next()) {
                long messageId = rs.getLong("ID_MESSAGE");
                java.sql.Date messageDate = rs.getDate("DATE");
                String content = rs.getString("CONTENT");

                Message m = new Message(messageId, content, messageDate, senderId);
                messageList.add(m);
            }
            rs.close();
            ps.close();
        }
        return messageList;
    }

    public List<Message> getReceiverMessages(long senderId) throws Exception {
        String messageQuery = "SELECT * FROM MESSAGE " +
                "WHERE (ID_SENDER = ?) " +
                "ORDER BY DATE";

        List<Message> messageList = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
            ps = con.prepareStatement(messageQuery);
            ps.setLong(1, senderId);

            rs = ps.executeQuery();

            while (rs.next()) {
                long messageId = rs.getLong("ID_MESSAGE");
                java.sql.Date messageDate = rs.getDate("DATE");
                String content = rs.getString("CONTENT");

                Message m = new Message(messageId, content, messageDate, senderId);
                messageList.add(m);
            }
            rs.close();
            ps.close();
        }
        return messageList;
    }

    public List<Message> getMessages(long senderId, long receiverId) throws Exception {
        String messageQuery = "SELECT * FROM MESSAGE " +
                "WHERE (ID_SENDER = ? AND ID_RECIPIENT = ?) " +
                "OR (ID_SENDER = ? AND ID_RECIPIENT = ?)" +
                "ORDER BY DATE";

        List<Message> messageList = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            preparedStatement = connection.prepareStatement(messageQuery);
            preparedStatement.setLong(1, senderId);
            preparedStatement.setLong(2, receiverId);
            preparedStatement.setLong(3, receiverId);
            preparedStatement.setLong(4, senderId);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long messageId = rs.getLong("ID_MESSAGE");
                Date date = rs.getTimestamp("DATE");
                String content = rs.getString("CONTENT");
                long messageSenderId = rs.getLong("ID_SENDER");
                long messageReceiverId = rs.getLong("ID_RECIPIENT");

                Message message = new Message(messageId, content, date, (new Person(messageSenderId)), (new Person(messageReceiverId)));
                messageList.add(message);
            }
            rs.close();
            preparedStatement.close();
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

    public Person getReceiverId(String receiverEmail) throws Exception {
        String messageQuery = "SELECT * FROM DONATOR " +
                "WHERE EMAIL = ? ";

        PreparedStatement ps = null;
        ResultSet rs = null;
        Person receiver = null;

        try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
            ps = con.prepareStatement(messageQuery);
            ps.setString(1, receiverEmail);

            rs = ps.executeQuery();
            while (rs.next()) {
                long donatorId = rs.getLong("ID_DONATOR");
                String donatorType = rs.getString("TYPE");
                if (donatorType.equals("P")){
                    String donatorName = rs.getString("NAME");
                    String donatorSurname = rs.getString("SURNAME");

                    receiver = new Person();
                    receiver.setId(donatorId);
                    receiver.setName(donatorName);
                    receiver.setSurname(donatorSurname);
                    receiver.setEmail(receiverEmail);
                }

            }
            rs.close();
            ps.close();
        }
        return receiver;
    }
}
