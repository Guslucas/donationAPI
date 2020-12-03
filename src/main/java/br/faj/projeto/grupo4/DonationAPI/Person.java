package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Person extends Donator {

    private String cpf;
    private String name;
    private String surname;
    private Date birthDate;

    public Person(long id, String email, String password, String bio, Address address, String cpf, String name, String surname, Date birthDate) {
        super(id, email, password, bio, address);
        this.cpf = cpf;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public Person(long id, String email, String name, String surname) {
        super(id, email);
        this.name = name;
        this.surname = surname;
    }

    public Person(long id){
        super(id);
    }
    public Person(){
        super();
    }

    public String getCpf() {
        return cpf;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Date getBirthDate() {
        return birthDate;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
