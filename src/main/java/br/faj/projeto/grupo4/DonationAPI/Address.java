package br.faj.projeto.grupo4.DonationAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class Address {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String cep;
    private String state;

    public Address(String street, String number, String complement, String neighborhood, String city, String cep, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.cep = cep;
        this.state = state;
    }
    public Address(){

    }

    public String getStreet() {
        return street;
    }
    public String getNumber() {
        return number;
    }
    public String getComplement() {
        return complement;
    }
    public String getNeighborhood() {
        return neighborhood;
    }
    public String getCity() {
        return city;
    }
    public String getCep() {
        return cep;
    }
    public String getState() {
        return state;
    }

    public void getAddress(){

    }
}
