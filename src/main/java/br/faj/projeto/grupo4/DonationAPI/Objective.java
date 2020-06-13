package br.faj.projeto.grupo4.DonationAPI;

public class Objective {
    private long id;
    private int quantity;
    private Product product;
    private Campaign campaign;

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
