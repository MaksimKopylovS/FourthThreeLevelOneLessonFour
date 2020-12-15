package max_s.k.springboot.services;

import max_s.k.springboot.model.Product;
import max_s.k.springboot.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepositories productRepositories;

    @Autowired
    public ProductService(ProductRepositories productRepositories){
        this.productRepositories = productRepositories;
    }

    public List<Product> showAll(){
        return productRepositories.showAll();
    }

    public List<Product> showAll(Integer minCost, Integer maxCost){
        return productRepositories.showAll(minCost, maxCost);
    }

    public void addProduct(String title, Integer cost){
        productRepositories.addProduct(title, cost);
    }

    public void deleteProduct(Long id){
        productRepositories.deletePoduct(id);
    }

    public void editProduct(Long id, String title, Integer cost){
        productRepositories.editProducts(id, title, cost);
    }


}
