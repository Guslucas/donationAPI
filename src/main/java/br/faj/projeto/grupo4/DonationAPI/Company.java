package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Company extends Donator {

    private String cnpj;
    private String tradingName;
    private String companyName;
    private Date foundationDate;

    public Company(long id, String email, String password, String bio, Address address, String cnpj, String tradingName, String companyName, Date foundationDate) {
        super(id, email, password, bio, address);
        this.cnpj = cnpj;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.foundationDate = foundationDate;
    }

    public Company() { }

    public Company(long donatorId, String receiverEmail, String donatorTradingName) {
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
