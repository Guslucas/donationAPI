package br.faj.projeto.grupo4.DonationAPI;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductDonation.class),
        @JsonSubTypes.Type(value = MoneyDonation.class)
})

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
