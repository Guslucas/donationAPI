package br.faj.projeto.grupo4.DonationAPI.Dao;

import br.faj.projeto.grupo4.DonationAPI.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Types;

@Repository
public class DonationDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Donation donation(Donation donation) throws Exception{
        String insertMoneyDonation = "INSERT INTO DONATION (ID_DONATOR, ID_CAMPAIGN, TYPE, MONETARY_VALUE, DATE)" +
                "VALUES (?, ?, ?, ?, ?);";
        String insertProductDonation = "INSERT INTO DONATION (ID_DONATOR, ID_CAMPAIGN, TYPE, MONETARY_VALUE, DATE)" +
                "VALUES (?, ?, ?, Null, ?);";

        PreparedStatement preparedStatement = null;

        try (Connection connection = jdbcTemplate.getDataSource().getConnection()){
                if (donation instanceof MoneyDonation) {
                    preparedStatement = connection.prepareStatement(insertMoneyDonation, Statement.RETURN_GENERATED_KEYS);
                    MoneyDonation moneyDonation = (MoneyDonation) donation;
                    Campaign campaign = donation.getCampaign();

                    preparedStatement.setLong(1, moneyDonation.getDonator().getId());
                    if (campaign == null) {
                        preparedStatement.setNull(2, Types.NUMERIC);
                    }
                    else{
                        preparedStatement.setLong(2, moneyDonation.getCampaign().getId());
                    }
                    preparedStatement.setString(3, "M");
                    preparedStatement.setFloat(4, moneyDonation.getQuantity());
                    preparedStatement.setDate(5, new java.sql.Date(moneyDonation.getDate().getTime()));
                }
                else if (donation instanceof ProductDonation){
                    preparedStatement = connection.prepareStatement(insertProductDonation, Statement.RETURN_GENERATED_KEYS);
                    ProductDonation productDonation = (ProductDonation) donation;
                    Campaign campaign = donation.getCampaign();

                    preparedStatement.setLong(1, productDonation.getDonator().getId());
                    if (campaign == null) {
                        preparedStatement.setNull(2, Types.NUMERIC);
                    }
                    else{
                        preparedStatement.setLong(2, productDonation.getCampaign().getId());
                    }
                    preparedStatement.setString(3, "P");
                    preparedStatement.setNull(4, Types.NULL);
                    preparedStatement.setDate(5, new java.sql.Date(productDonation.getDate().getTime()));
                }

                int result = preparedStatement.executeUpdate();
            }
        return donation;
    }

    public Donation campaignDonation(Donation donation) throws Exception{
        String sqlInsert = "";
        return donation;
    }

}
