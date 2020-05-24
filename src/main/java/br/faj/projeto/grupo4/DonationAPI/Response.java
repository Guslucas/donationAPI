package br.faj.projeto.grupo4.DonationAPI;

public class Response {

    public String status;
    public String errorMessage;
    public Object objective;

    public Response(Object obj){
        this.status = "Ok";
        this.objective = obj;
    }

    public Response(String message) {
        this.status = "Erro";
        this.errorMessage = message;
    }
}
