package br.faj.projeto.grupo4.DonationAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDonation extends Donation {
    List<Item> items = new ArrayList<>();
    boolean deliver;

    public ProductDonation(long id, Date date, Campaign campaign, Donator donator, DonationStatus status, boolean deliver, List<Item> items, boolean deliver1) {
        super(id, date, campaign, donator, status, deliver);
        this.items = items;
        this.deliver = deliver1;
    }

    public List<Item> getItems() {
        return items;
    }

}
