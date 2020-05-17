package br.faj.projeto.grupo4.DonationAPI;

public class Person extends Donator {

    String cpf;
    String name;
    String surname;
    String birthDate;

    public Person(long id, String email, String password, String bio, Address address, String cpf, String name, String surname, String birthDate) {
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

    public String getBirthDate() {
        return birthDate;
    }
}
