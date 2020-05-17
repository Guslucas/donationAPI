package br.faj.projeto.grupo4.DonationAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoneyCampaign extends Campaign {

    float goal;

    public MoneyCampaign(long id, String name, String description, Date startDate, Date endDate, float goal) {
        super(id, name, description, startDate, endDate);
        this.goal = goal;
    }

    public float getGoal() {
        return goal;
    }

}
