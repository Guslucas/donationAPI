package br.faj.projeto.grupo4.DonationAPI;

public class Objective {
    long id;
    int quantity;
    Product product;

    public Objective(long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
}
