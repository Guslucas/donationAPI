package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Campaign;
import br.faj.projeto.grupo4.DonationAPI.Dao.CampaignDAO;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CampaignController {
    @Autowired
    CampaignDAO dao;

    @GetMapping("/Campaign")
    public List<Campaign> getCampaigns(){
        return dao.getCampaigns();
    }

    @PostMapping("/Campaign")
    public Campaign campaign(@RequestBody MoneyCampaign mc){
        dao.inserir(mc);
        return mc;
    }
}
