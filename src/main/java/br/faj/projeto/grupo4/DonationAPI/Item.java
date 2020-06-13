package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Item {

    private long id;
    private Date shelfLife;
    private Product product;
    private String barCode;
    private String brand;

    public Item(long id, Date shelfLife, Product product, String barCode, String brand) {
        this.id = id;
        this.shelfLife = shelfLife;
        this.product = product;
        this.barCode = barCode;
        this.brand = brand;
    }
    public Item(){}

    public long getId() {
        return id;
    }
    public Date getShelfLife() {
        return shelfLife;
    }
    public Product getProduct() {
        return product;
    }
    public String getBarCode() {
        return barCode;
    }
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", shelfLife=" + shelfLife +
                ", product=" + product +
                ", barCode='" + barCode + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
