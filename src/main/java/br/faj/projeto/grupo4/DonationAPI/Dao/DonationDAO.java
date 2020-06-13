package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DonationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Donation donation(Donation donation) throws Exception {
        String insertMoneyDonation = "INSERT INTO DONATION (ID_DONATOR, ID_CAMPAIGN, TYPE, MONETARY_VALUE, DATE)" +
                "VALUES (?, ?, ?, ?, ?);";
        String insertProductDonation = "INSERT INTO DONATION (ID_DONATOR, ID_CAMPAIGN, TYPE, MONETARY_VALUE, DATE)" +
                "VALUES (?, ?, ?, Null, ?);";

        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            if (donation instanceof MoneyDonation) {
                preparedStatement = connection.prepareStatement(insertMoneyDonation, Statement.RETURN_GENERATED_KEYS);
                MoneyDonation moneyDonation = (MoneyDonation) donation;
                Campaign campaign = donation.getCampaign();

                preparedStatement.setLong(1, moneyDonation.getDonator().getId());
                if (campaign == null) {
                    preparedStatement.setNull(2, Types.NUMERIC);
                } else {
                    preparedStatement.setLong(2, moneyDonation.getCampaign().getId());
                }
                preparedStatement.setString(3, "M");
                preparedStatement.setFloat(4, moneyDonation.getQuantity());
                preparedStatement.setDate(5, new java.sql.Date(moneyDonation.getDate().getTime()));
            } else if (donation instanceof ProductDonation) {
                preparedStatement = connection.prepareStatement(insertProductDonation, Statement.RETURN_GENERATED_KEYS);
                ProductDonation productDonation = (ProductDonation) donation;
                Campaign campaign = donation.getCampaign();

                preparedStatement.setLong(1, productDonation.getDonator().getId());
                if (campaign == null) {
                    preparedStatement.setNull(2, Types.NUMERIC);
                } else {
                    preparedStatement.setLong(2, productDonation.getCampaign().getId());
                }
                preparedStatement.setString(3, "P");
                preparedStatement.setDate(4, new java.sql.Date(productDonation.getDate().getTime()));
            }
            int result = preparedStatement.executeUpdate();

            if (result > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                long id = generatedKeys.getLong(1);
                generatedKeys.close();

                if (donation instanceof ProductDonation) {
                    ProductDonation productDonation = (ProductDonation) donation;
                    List<Item> itemList = productDonation.getItems();

                    for (int i = 0; i <= itemList.size(); i++) {
                        insertItem(itemList.get(i), id);
                    }
                }
            }
            preparedStatement.close();
        }
        return donation;
    }

    public void insertItem(Item item, long id) throws Exception {
        String insertItem = "INSERT INTO ITEM (SHELF_LIFE, ID_PRODUCT, ID_DONATION, BARCODE, BRAND) " +
                "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement2 = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            preparedStatement2 = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS);

            preparedStatement2.setNull(1, Types.DATE);
            preparedStatement2.setLong(2, item.getProduct().getId());
            preparedStatement2.setLong(3, id);
            preparedStatement2.setNull(4, Types.VARCHAR);
            preparedStatement2.setNull(5, Types.VARCHAR);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int result = preparedStatement2.executeUpdate();
        preparedStatement2.close();
    }
}
