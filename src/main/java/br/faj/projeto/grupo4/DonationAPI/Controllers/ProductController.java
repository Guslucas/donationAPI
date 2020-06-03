package br.faj.projeto.grupo4.DonationAPI.Controllers;

import br.faj.projeto.grupo4.DonationAPI.Dao.ProductDAO;
import br.faj.projeto.grupo4.DonationAPI.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductDAO dao;

    @GetMapping("/product")
    public List<Product> getProduct(){
        return dao.getProducts();
    }
}
