package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public abstract class Campaign {
    long id;
    String name;
    String description;
    Date startDate;
    Date endDate;

    public Campaign(long id, String name, String description, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
