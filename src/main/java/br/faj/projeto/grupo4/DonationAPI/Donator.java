package br.faj.projeto.grupo4.DonationAPI;

import java.util.ArrayList;
import java.util.List;

public class Donator {
    long id;
    String email;
    String password;
    String bio;
    Address address;
    String type;

    public Donator(long id, String email, String password, String bio, Address address, String type) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.address = address;
        this.type = type;
    }

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getBio() {
        return bio;
    }
    public Address getAddress() {
        return address;
    }
    public String getType() {
        return type;
    }

    public List<Address> generateAddress(){
        List<Address> addressList = new ArrayList<>();

        String street = address.getStreet();
        String number = address.getNumber();
        String complement = address.getComplement();
        String neighborhood = address.getNeighborhood();
        String city = address.getCity();
        String cep = address.getCep();
        String state = address.getState();

        Address address1 =  new Address(street, number, complement, neighborhood, city, cep, state);
        addressList.add(address1);
        return addressList;
    }
}
