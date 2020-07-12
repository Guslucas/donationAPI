package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.LeaderboardDAO;
import br.faj.projeto.grupo4.DonationAPI.LeaderboardItem;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaderboardController {
    @Autowired
    LeaderboardDAO dao;

    @GetMapping("/leaderboard")
    public Response getLeaderboard(){
        try{
            return new Response(dao.getLeaderboard());
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
