package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Item {

    long id;
    Date shelfLife;
    Product product;
    Donation donation;
    String barCode;
    String brand;

    public Item(long id, Date shelfLife, Product product, Donation donation, String barCode, String brand) {
        this.id = id;
        this.shelfLife = shelfLife;
        this.product = product;
        this.donation = donation;
        this.barCode = barCode;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }
    public Date getShelfLife() {
        return shelfLife;
    }
    public Product getProduct() {
        return product;
    }
    public Donation getDonation() {
        return donation;
    }
    public String getBarCode() {
        return barCode;
    }
    public String getBrand() {
        return brand;
    }
}
