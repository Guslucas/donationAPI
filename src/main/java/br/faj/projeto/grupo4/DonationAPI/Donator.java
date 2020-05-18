package br.faj.projeto.grupo4.DonationAPI;

public abstract class Donator {
    long id;
    String email = "teste";
    String password = "1234";
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




}
