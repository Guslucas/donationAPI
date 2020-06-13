package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.ProductDAO;
import br.faj.projeto.grupo4.DonationAPI.Product;
import br.faj.projeto.grupo4.DonationAPI.Response;
//import com.sun.jdi.event.ExceptionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDAO dao;

    @GetMapping("/product")
    public Response getProduct(){
        try {
            return new Response(dao.getProducts());
        }catch (Exception ex){
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }

    @PostMapping("/product")
    public Response addProduct(@RequestBody Product product){
        try {
            System.out.println(product);
            return new Response(dao.addProduct(product));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response(ex.getMessage());
        }
    }
}
