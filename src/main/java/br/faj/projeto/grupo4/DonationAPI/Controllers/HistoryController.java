package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.HistoryDAO;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {
    @Autowired
    HistoryDAO dao;

    @GetMapping("/donator/{id}/donation")
    public Response getHistory(@PathVariable ("id") long id){
        try{
            return new Response(dao.getHistory(id));
        }catch (Exception ex){
            return new Response (ex.getMessage());
        }
    }
}
