package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.LoginDAO;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginDAO dao;

    @PostMapping("/login")
    public Response login(@RequestBody Donator d){
        try{
            return new Response (dao.login(d));
        } catch (Exception ex){
            return new Response(ex.getMessage());
        }
    }
}
