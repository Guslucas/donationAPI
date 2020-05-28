package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Company;
import br.faj.projeto.grupo4.DonationAPI.Dao.SignInDAO;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Person;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInController {
    @Autowired
    SignInDAO dao;

    @PostMapping ("/signin")
    public Response postDonator (@RequestBody Donator donator){
        try {
            System.out.println(donator.toString());
            return new Response (dao.cadastrar(donator));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
