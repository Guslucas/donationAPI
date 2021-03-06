package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class MoneyDonation extends Donation {

    private float quantity;

    public MoneyDonation(long id, Date date, Campaign campaign, Donator donator, boolean deliver, float quantity) {
        super(id, date, campaign, donator, deliver);
        this.quantity = quantity;
    }

    public float getQuantity() {
        return quantity;
    }
}
