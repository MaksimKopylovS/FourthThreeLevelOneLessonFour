package max_s.k.springboot.repositories;

import max_s.k.springboot.exceptions.ResourceNotFoundException;
import max_s.k.springboot.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepositories {

    List<Product> productList;

    @PostConstruct
    public void init() {
        productList = new ArrayList<>();
        productList.add(new Product(1L, "Помидоры", 100));
        productList.add(new Product(2L, "Огурцы", 200));
        productList.add(new Product(3L, "Капуста", 300));
        productList.add(new Product(4L, "Марковка", 400));
        productList.add(new Product(5L, "Картошка", 500));
    }

    public List<Product> showAll() {
        return Collections.unmodifiableList(productList);
    }

    public List<Product> showAll(Integer minCost, Integer maxCost){
        List<Product> out = showAll();
        if(minCost !=null){
            out = out.stream().filter(s -> s.getCost() >= minCost).collect(Collectors.toList());
        }
        if (maxCost !=null){
            out = out.stream().filter(s -> s.getCost() <= maxCost).collect(Collectors.toList());
        }
        return out;
    }

    public void addProduct(String title, Integer cost) {
        productList.add(new Product(maxId() + 1, title, cost));
    }

    private Long maxId() {
        long max = 0;
        for (Product p : productList) {
            if (p.getId() > max) {
                max = p.getId();
            }
        }
        return max;
    }

    public void deletePoduct(Long id) {
        productList.removeIf(s -> s.getId().equals(id));
    }

    public void editProducts(Long id, String title, Integer cost) {
        Product product = new Product(id, title, cost);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(product.getId())) {
                productList.set(i, product);
            }
        }
    }


}
