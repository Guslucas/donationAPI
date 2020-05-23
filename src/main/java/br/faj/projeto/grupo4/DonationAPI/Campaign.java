package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public abstract class Campaign {
    long id;
    String name;
    String description;
    String type;
    java.sql.Date startDate;
    java.sql.Date endDate;

    public Campaign(long id, String name, String description, String type, java.sql.Date startDate, java.sql.Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
