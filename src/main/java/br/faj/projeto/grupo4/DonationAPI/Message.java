package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class Message {

    private long id;
    private String content;
    private Date date;
    private long senderId;
    private long receiverId;
    private Donator sender;
    private Donator receiver;

    public Message(long id, String content, Date date, Donator sender, Donator receiver) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(long id, String content, Date date, long senderId, long receiverId) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Message(String content, Date date, long senderId, long receiverId) {
        this.content = content;
        this.date = date;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public Message(long id, String content, Date date, long senderId) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.senderId = senderId;
    }

    public Message(){}

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
    public long getSenderId() {
        return senderId;
    }
    public long getReceiverId() {
        return receiverId;
    }
    public Donator getReceiver() {
        return receiver;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setSenderId(long sender) {
        this.senderId = senderId;
    }
    public void setSender(Donator sender) {
        this.sender = sender;
    }
    public void setReceiver(Donator receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}
