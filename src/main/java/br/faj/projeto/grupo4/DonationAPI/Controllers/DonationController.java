package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.DonationDAO;
import br.faj.projeto.grupo4.DonationAPI.Donation;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {
    @Autowired
    DonationDAO dao;

    @PostMapping("/donation")
    public Response donation(@RequestBody Donation donation){
        try {
            System.out.println(donation.toString());
            System.out.println(donation.getDonator().getId());
//            System.out.println(donation.get());
            System.out.println(donation.getDate());
            System.out.println(donation.getCampaign().getId());
            return new Response(dao.donation(donation));
        } catch (Exception ex){
            return new Response(ex.getMessage());
        }
    }

    @PostMapping("/campaign/donation")
    public Response campaignDonation(@RequestBody Donation donation){
        try {
            return new Response(dao.campaignDonation(donation));
        } catch (Exception ex){
            return new Response(ex.getMessage());
        }
    }
}
