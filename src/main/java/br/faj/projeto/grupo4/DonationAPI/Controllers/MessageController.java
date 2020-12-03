package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.MessageDAO;
import br.faj.projeto.grupo4.DonationAPI.Donator;
import br.faj.projeto.grupo4.DonationAPI.Message;
import br.faj.projeto.grupo4.DonationAPI.Person;
import br.faj.projeto.grupo4.DonationAPI.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
    public Response sendMessage(@PathVariable ("senderId") long senderId, @PathVariable ("receiverId") long receiverId, @RequestParam (required = false) Message message){
        try {
            System.out.println();
            return new Response(dao.sendMessage(message, senderId));
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @PostMapping("/donator/{senderId}/{receiverId}/newMessage/web")
    public Response sendMessage(@PathVariable ("senderId") long senderId, @PathVariable ("receiverId") long receiverId, @RequestParam (value = "content") String content){
        try {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            Message message = new Message(content, date, senderId, receiverId);
            return new Response(dao.sendMessageWeb(message));
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
