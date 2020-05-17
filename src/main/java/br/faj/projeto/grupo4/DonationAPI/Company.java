package br.faj.projeto.grupo4.DonationAPI;

public class Company extends Donator {

    String cnpj;
    String tradingName;
    String companyName;
    String foundationDate;

    public Company(long id, String email, String password, String bio, Address address, String cnpj, String tradingName, String companyName, String foundationDate) {
        super(id, email, password, bio, address);
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

    public String getFoundationDate() {
        return foundationDate;
    }
}
