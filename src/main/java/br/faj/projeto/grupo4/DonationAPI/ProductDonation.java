package br.faj.projeto.grupo4.DonationAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDonation extends Donation {

    private List<Item> items = new ArrayList<>();

    public ProductDonation(long id, Date date, Campaign campaign, Donator donator, boolean deliver, List<Item> items) {
        super(id, date, campaign, donator, deliver);
        this.items = items;
    }

    public ProductDonation(){}

    public ProductDonation(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

}
