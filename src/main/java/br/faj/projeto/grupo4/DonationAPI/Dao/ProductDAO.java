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

    public List<Product> getProducts() throws Exception {
        String query = "SELECT * FROM PRODUCT";
        List<Product> productList = new ArrayList<>();

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ID_PRODUCT");
                String type = rs.getString("TYPE");
                String name = rs.getString("NAME");

                Product p = new Product(id, type, name);
                productList.add(p);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product addProduct(Product product) throws Exception {
        String insertProduct = "INSERT INTO PRODUCT (NAME, TYPE) VALUES (?, ?)";

        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){

            preparedStatement = connection.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getType());

            int result = preparedStatement.executeUpdate();
            if (result == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                long id = generatedKeys.getLong(1);
                product.setId(id);
                generatedKeys.close();
                return product;
            }
        } finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        throw new Exception("Erro.");
    }
}
