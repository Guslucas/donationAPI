//package br.faj.projeto.grupo4.DonationAPI.Dao;
//
//import br.faj.projeto.grupo4.DonationAPI.Company;
//import br.faj.projeto.grupo4.DonationAPI.Donator;
//import br.faj.projeto.grupo4.DonationAPI.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.Connection;
//import java.sql.Date;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//@Repository
//public class SignInDAO {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public Donator cadastrar (Donator donator){
//        String insert = "INSERT INTO DONATOR (ID, EMAIL, PASSWORD, BIO, TYPE, CPF, NAME, SURNAME," +
//                "BIRTHDATE, CNPJ, TRADING_NAME, COMPANY_NAME, FOUNDATIONDATE, ID_ADDRESS)" +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(insert);
//            preparedStatement.setLong(1, donator.getId());
//            preparedStatement.setString(2, donator.getEmail());
//            preparedStatement.setString(3, donator.getPassword());
//            preparedStatement.setString(4, donator.getBio());
//            preparedStatement.setString(5, donator.getType());
//            if (donator.getType().equals("Person")){
//                Person person = (Person) donator;
//                preparedStatement.setString(6, person.getCpf());
//                preparedStatement.setString(7, person.getName());
//                preparedStatement.setString(8, person.getSurname());
//                preparedStatement.setDate(9, (Date) person.getBirthDate());
//                preparedStatement.setString(14, person.getAddressFicticio());
//                return new Person();
//            }
//            else if(donator.getType().equals("Company")){
//                Company company = (Company) donator;
//                preparedStatement.setString(10, company.getCnpj());
//                preparedStatement.setString(11, company.getTradingName());
//                preparedStatement.setString(12, company.getCompanyName());
//                preparedStatement.setDate(13, (Date) company.getFoundationDate());
//                preparedStatement.setString(14, company.getAddressFicticio());
//                return company;
//            }
//            int result = preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
