package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Address;
import br.faj.projeto.grupo4.DonationAPI.Company;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Person;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class LoginDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Donator login (Donator donator) throws Exception {
        checkLogin (donator, true);

        String query = "SELECT * FROM Donator "
                + "WHERE Email = '" + donator.getEmail() + "' and Password = '" + donator.getPassword() + "'";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                long id = rs.getLong("ID_DONATOR");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                String bio = rs.getString("BIO");
                String street = rs.getString("STREET");
                String number = rs.getString("NUMBER");
                String complement = rs.getString("COMPLEMENT");
                String neighborhood = rs.getString("NEIGHBORHOOD");
                String city = rs.getString("CITY");
                String cep = rs.getString("CEP");
                String state = rs.getString("STATE");
                String donatorType = rs.getString("TYPE");

                if (donatorType.equals("P")){
                    String cpf = rs.getString("CPF");
                    String name = rs.getString("NAME");
                    String surname = rs.getString("SURNAME");
                    Date birthDate = rs.getDate("BIRTH_DATE");
                    return new Person(id, email, password, bio, new Address(street, number, complement, neighborhood, city, cep, state), donatorType, cpf, name, surname, birthDate);
                }
                else if (donatorType.equals("C")){
                    String cnpj = rs.getString("CNPJ");
                    String tradingName = rs.getString("TRADING_NAME");
                    String companyName = rs.getString("COMPANY_NAME");
                    Date foundationDate = rs.getDate("FOUNDATION_DATE");
                    return new Company(id, email, password, bio, new Address(street, number, complement, neighborhood, city, cep, state), donatorType, cnpj, tradingName, companyName, foundationDate);
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private void checkLogin(Donator donator, boolean login) throws Exception{
        if(donator.getEmail() == null || donator.getEmail().trim().isEmpty()){
            throw new Exception("Email não inserido.");
        }
        if(donator.getPassword() == null || donator.getPassword().trim().isEmpty()){
            throw new Exception("Senha não inserida");
        }
    }
}
