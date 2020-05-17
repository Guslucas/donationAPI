package br.faj.projeto.grupo4.DonationAPI;

public class Product {
    long id;
    String description;
    String name;
    Category category;

    public Product(long id, String description, String name, Category category) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.category = category;
    }

    public long getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public Category getCategory() {
        return category;
    }
}
