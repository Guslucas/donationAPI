package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public abstract class Donation {

    long id;
    Date date;
    Campaign campaign;
    Donator donator;
    DonationStatus status;
    boolean deliver;

    public Donation(long id, Date date, Campaign campaign, Donator donator, DonationStatus status, boolean deliver) {
        this.id = id;
        this.date = date;
        this.campaign = campaign;
        this.donator = donator;
        this.status = status;
        this.deliver = deliver;
    }

    public long getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }
    public Campaign getCampaign() {
        return campaign;
    }
    public Donator getDonator() {
        return donator;
    }
    public DonationStatus getStatus() {
        return status;
    }
    public boolean isDeliver() {
        return deliver;
    }
}
