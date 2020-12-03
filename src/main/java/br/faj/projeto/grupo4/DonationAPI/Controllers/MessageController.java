package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.MessageDAO;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Message;
import br.faj.projeto.grupo4.DonationAPI.Person;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class MessageController {
    @Autowired
    MessageDAO dao;

    @GetMapping("/donator/{senderId}/{receiverId}/message")
    public Response getMessages(@PathVariable("senderId") long senderId, @PathVariable("receiverId") long receiverId){
        try {
            return new Response(dao.getMessages(senderId, receiverId));
        } catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @PostMapping("/donator/{senderId}/{receiverId}/message/new")
    public Response sendMessage(@PathVariable ("senderId") long senderId, @PathVariable ("receiverId") long receiverId, @RequestBody (required = true) Message message){
        try {
            return new Response(dao.sendMessage(message, senderId));
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @PostMapping("/donator/getReceiverId")
    public Response getReceiverId(@RequestParam(value = "receiverEmail") String receiverEmail){
        try {
            return new Response(dao.getReceiverId(receiverEmail));
        } catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
