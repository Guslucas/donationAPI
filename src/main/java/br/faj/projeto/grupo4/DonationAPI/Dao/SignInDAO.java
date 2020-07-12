package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.Address;
import br.faj.projeto.grupo4.DonationAPI.Company;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class SignInDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Donator cadastrar (Donator donator) throws Exception{

        String insertPerson = "INSERT INTO Donator (Email, Password, BIO, Type, CPF, Name, Surname, BIRTH_DATE," +
                "STREET, Number , COMPLEMENT, NEIGHBORHOOD, CITY, CEP, STATE)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertCompany = "INSERT INTO Donator (Email, Password, BIO, Type, " +
                "CNPJ, Trading_Name, Company_Name, FOUNDATION_DATE," +
                "STREET, Number, COMPLEMENT, NEIGHBORHOOD, CITY, CEP, STATE)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {

            if (donator instanceof Person){
                preparedStatement = connection.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS);
                Person person = (Person) donator;
                System.out.println(person);
                preparedStatement.setString(4, "P");
                preparedStatement.setString(5, person.getCpf());
                preparedStatement.setString(6, person.getName());
                preparedStatement.setString(7, person.getSurname());
                preparedStatement.setDate(8, new Date (person.getBirthDate().getTime()));
            }
            else if(donator instanceof Company){
                preparedStatement = connection.prepareStatement(insertCompany, Statement.RETURN_GENERATED_KEYS);
                Company company = (Company) donator;
                System.out.println(company);
                preparedStatement.setString(4, "C");
                preparedStatement.setString(5, company.getCnpj());
                preparedStatement.setString(6, company.getTradingName());
                preparedStatement.setString(7, company.getCompanyName());
                preparedStatement.setDate(8, new Date(company.getFoundationDate().getTime()));
            }

            preparedStatement.setString(1, donator.getEmail());
            preparedStatement.setString(2, donator.getPassword());
            preparedStatement.setString(3, donator.getBio());
            Address donatorAddress = donator.getAddress();
            preparedStatement.setString(9, donatorAddress.getStreet());
            preparedStatement.setString(10, donatorAddress.getNumber());
            preparedStatement.setString(11, donatorAddress.getComplement());
            preparedStatement.setString(12, donatorAddress.getNeighborhood());
            preparedStatement.setString(13, donatorAddress.getCity());
            preparedStatement.setString(14, donatorAddress.getCep());
            preparedStatement.setString(15, donatorAddress.getState());

            int result = preparedStatement.executeUpdate();
            if (result == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                long id = generatedKeys.getLong(1);
                donator.setId(id);
                generatedKeys.close();
                return donator;
            }

        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        throw new Exception("Erro.");
    }
}
