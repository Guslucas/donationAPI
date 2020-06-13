package br.faj.projeto.grupo4.DonationAPI;

import java.util.List;

public class Response {

    private String status;
    private String errorMessage;
    private Object object;

    public Response(Object obj){
        this.status = "Ok";
        this.object = obj;
    }
    public Response(String message){
        this.status = "Erro";
        this.errorMessage = message;
    }

    public String getStatus() {
        return status;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public Object getObject() {
        return object;
    }
}
