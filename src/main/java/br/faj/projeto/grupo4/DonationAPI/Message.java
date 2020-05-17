package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Message {

    long id;
    String content;
    Date date;
    Donator sender;
    Donator receiver;

    public Message(long id, String content, Date date, Donator sender, Donator receiver) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public Date getDate() {
        return date;
    }
    public Donator getSender() {
        return sender;
    }
    public Donator getReceiver() {
        return receiver;
    }
}
