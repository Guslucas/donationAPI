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

    @GetMapping("/donator/{senderId}/senderMessage")
    public Response getSenderMessages(@PathVariable("senderId") long senderId){
        try {
            return new Response(dao.getSenderMessages(senderId));
        } catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @GetMapping("/donator/{receiverId}/receiverMessage")
    public Response getReceiverMessages(@PathVariable("receiverId") long receiverId){
        try {
            return new Response(dao.getReceiverMessages(receiverId));
        } catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    /*@PostMapping("/donator/{id}/message")
    public Response getMessages(@PathVariable("id") long senderId, @RequestBody Donator receiver){
       try {
           return new Response(dao.getMessages(senderId, receiver));
       } catch (Exception ex){
           ex.printStackTrace();
           return new Response(ex.getMessage());
       }
    }*/

    @PostMapping("/donator/{senderId}/{receiverId}/message/new")
    public Response sendMessage(@PathVariable ("senderId") long senderId, @PathVariable ("receiverId") long receiverId, @RequestParam (required = true) String message){
        try {
            return new Response(dao.sendMessageTeste(message, senderId, receiverId));
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
