package br.faj.projeto.grupo4.DonationAPI;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Person.class),
        @JsonSubTypes.Type(value = Company.class)
})
public abstract class Donator {
    long id;
    String email;
    String password;
    String bio;
    Address address;

    public Donator(long id, String email, String password, String bio, Address address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.address = address;
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

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Donator{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", address=" + address +
                '}';
    }
}
