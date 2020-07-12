package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.MessageDAO;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Message;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MessageController {
    @Autowired
    MessageDAO dao;

    @PostMapping("/donator/{id}/message")
    public Response getMessages(@PathVariable("id") long senderId, @RequestBody Donator receiver){
       try {
           return new Response(dao.getMessages(senderId, receiver));
       } catch (Exception ex){
           ex.printStackTrace();
           return new Response(ex.getMessage());
       }
    }

    @PostMapping("/donator/{id}/message/new")
    public Response sendMessage(@PathVariable ("id") long senderId, @RequestBody Message message){
        try {
            System.out.println(message);
            return new Response(dao.sendMessage(message, senderId));
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
