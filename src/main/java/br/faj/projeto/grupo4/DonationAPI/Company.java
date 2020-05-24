package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Company extends Donator {

    String cnpj;
    String tradingName;
    String companyName;
    Date foundationDate;

    public Company(long id, String email, String password, String bio, Address address, String type, String cnpj, String tradingName, String companyName, Date foundationDate) {
        super(id, email, password, bio, address, type);
        this.cnpj = cnpj;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.foundationDate = foundationDate;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Date getFoundationDate() {
        return foundationDate;
    }
}
