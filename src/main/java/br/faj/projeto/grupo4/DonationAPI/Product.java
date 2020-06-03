package br.faj.projeto.grupo4.DonationAPI;

public class Product {
    long id;
    String type;
    String name;

    public Product(long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public String getDescription() {
        return type;
    }
    public String getName() {
        return name;
    }
}
