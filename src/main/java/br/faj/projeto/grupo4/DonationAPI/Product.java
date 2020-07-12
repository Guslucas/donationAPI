package br.faj.projeto.grupo4.DonationAPI;

public class Product {
    private long id;
    private String type;
    private String name;

    public Product(long id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
    public Product(){}

    public long getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                '}';
    }
}
