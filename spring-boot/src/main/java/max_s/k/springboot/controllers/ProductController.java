package max_s.k.springboot.controllers;

import max_s.k.springboot.exceptions.ResourceNotFoundException;
import max_s.k.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") Integer minScore,
                          @RequestParam(required = false, name = "max_cost") Integer maxScore){
        model.addAttribute("products", productService.showAll(minScore, maxScore));
        return "products";
    }
//    Вытаскивание параметров из хтмл страницы
    @GetMapping("/add")
    public String addProducts(@RequestParam(name="title", required = false) String title,
                              @RequestParam(name = "cost", required = false) Integer cost){
        if(title.isEmpty() || cost == null){
            return "error_form";
        }
    productService.addProduct(title, cost);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String editProducts(@RequestParam(name="id", required = false) Long id,
                               @RequestParam(name="title", required = false) String title,
                               @RequestParam(name="cost", required = false) Integer cost){
        if(id == null || title.isEmpty() || cost == null){
            return "error_form";
        }
        productService.editProduct(id, title, cost);
        return "redirect:/products";
    }





}
