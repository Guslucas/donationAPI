package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getProduct(){
        String query = "SELECT * FROM PRODUCT";
        List<Product> productList = new ArrayList<>();

        try (Connection connection1 = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection1.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ID");
                String description = rs.getString("DESCRIPTION");
                String name = rs.getString("NAME");
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
