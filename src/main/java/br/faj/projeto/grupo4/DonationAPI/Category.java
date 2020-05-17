package br.faj.projeto.grupo4.DonationAPI;

import java.util.ArrayList;
import java.util.List;

public class Category {
    long id;
    String name;
    List<Product> products = new ArrayList<>();

    public Category(long id, String name, List<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Product> getProducts() {
        return products;
    }
}
