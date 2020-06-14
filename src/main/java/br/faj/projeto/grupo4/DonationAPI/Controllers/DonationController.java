package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.DonationDAO;
import br.faj.projeto.grupo4.DonationAPI.Donation;
import br.faj.projeto.grupo4.DonationAPI.Product;
import br.faj.projeto.grupo4.DonationAPI.ProductDonation;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {
    @Autowired
    DonationDAO dao;

    @PostMapping("/donate")
    public Response donation(@RequestBody Donation donation){
        try {
            if(donation instanceof ProductDonation){
                ProductDonation productDonation = (ProductDonation) donation;
                System.out.println(productDonation);
            }
            System.out.println(donation.toString());
            return new Response(dao.donation(donation));
        } catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

}
