package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Campaign;
import br.faj.projeto.grupo4.DonationAPI.Dao.CampaignDAO;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import br.faj.projeto.grupo4.DonationAPI.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {
    @Autowired
    CampaignDAO dao;

    @GetMapping("/campaign")
    public List<Campaign> getCampaigns(){
        return dao.getCampaigns();
    }

    @GetMapping("/campaign/{id}/product")
    public List<Product> getProductFromCampaign(@PathVariable("id") int id){
        return dao.getProductFromCampaign(id);
    }
}
