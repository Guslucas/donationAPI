package br.faj.projeto.grupo4.DonationAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Date;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductCampaign.class),
        @JsonSubTypes.Type(value = MoneyCampaign.class)
})

@JsonInclude(JsonInclude.Include.ALWAYS)
public abstract class Campaign {
    private long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private float percentage;
    private String type;

    public Campaign(long id, String name, String description, Date startDate, Date endDate, float percentage, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
        this.type = type;
    }
    public Campaign(){}

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

    public float getPercentage() {
        return percentage;
    }

    public void setId(long id) {
        this.id = id;
    }
}
