package br.faj.projeto.grupo4.DonationAPI;

import br.faj.projeto.grupo4.DonationAPI.Campaign;
import br.faj.projeto.grupo4.DonationAPI.Objective;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProductCampaign extends Campaign {

    List<Objective> objectives;

    public ProductCampaign(long id, String name, String description, String type, Date startDate, Date endDate, List<Objective> objectives) {
        super(id, name, description, type, startDate, endDate);
        this.objectives = objectives;
    }

    public List<Objective> getObjectives() {
        return objectives;
    }
}
