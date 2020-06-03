package br.faj.projeto.grupo4.DonationAPI;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MoneyCampaign extends Campaign {

    public MoneyCampaign(long id, String name, String description, String type, Date startDate, Date endDate, float percentage) {
        super(id, name, description, type, startDate, endDate, percentage);
    }

}
