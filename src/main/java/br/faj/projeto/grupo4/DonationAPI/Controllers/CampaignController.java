package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Campaign;
import br.faj.projeto.grupo4.DonationAPI.Dao.CampaignDAO;
import br.faj.projeto.grupo4.DonationAPI.MoneyCampaign;
import br.faj.projeto.grupo4.DonationAPI.Product;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CampaignController {
    @Autowired
    CampaignDAO dao;

    @GetMapping("/campaign")
    public Response getCampaigns(){
        try {
            return new Response(dao.getCampaigns());
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @GetMapping("/campaign/{id}/product")
    public Response getProductFromCampaign(@PathVariable("id") int id){
        try {
            return new Response(dao.getProductFromCampaign(id));
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
