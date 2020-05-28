package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Person extends Donator {

    String cpf;
    String name;
    String surname;
    Date birthDate;

    public Person(long id, String email, String password, String bio, Address address, String cpf, String name, String surname, Date birthDate) {
        super(id, email, password, bio, address);
        this.cpf = cpf;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
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

    @Override
    public String toString() {
        return "Person{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", address=" + address +
                '}';
    }
}
