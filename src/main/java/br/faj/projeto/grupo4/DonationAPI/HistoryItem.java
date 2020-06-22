package br.faj.projeto.grupo4.DonationAPI;

import java.util.Date;

public class HistoryItem {
    private Date date;
    private String type;

    public HistoryItem(Date date, String type) {
        this.date = date;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }
}
