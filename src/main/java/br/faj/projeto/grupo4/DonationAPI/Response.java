package br.faj.projeto.grupo4.DonationAPI;

public class Response {

    public String status;
    public String errorMessage;
    public Object object;

    public Response(Object obj){
        this.status = "Ok";
        this.object = obj;
    }

    public Response(String message) {
        this.status = "Erro";
        this.errorMessage = message;
    }
}
