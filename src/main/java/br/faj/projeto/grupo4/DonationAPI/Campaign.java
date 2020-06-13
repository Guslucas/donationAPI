package br.faj.projeto.grupo4.DonationAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductCampaign.class),
        @JsonSubTypes.Type(value = MoneyCampaign.class)
})

public abstract class Campaign {
    private long id;
    private String name;
    private String description;
    @JsonFormat (pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startDate;
    @JsonFormat (pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endDate;
    private float percentage;

    public Campaign(long id, String name, String description, Date startDate, Date endDate, float percentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
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

    public float getPercentage() {
        return percentage;
    }
}
