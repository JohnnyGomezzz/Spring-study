package ru.johnnygomezzz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showAllProductsPage(Model model, @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        Page<Product> page = productService.findAll(pageIndex - 1, 4);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/page")
    public String returnToPreviousPage(HttpServletRequest request) {
        return "redirect:"+ request.getHeader("Referer");
    }

    @GetMapping("/products/{id}")
    public String showProductInfo(@PathVariable(name = "id") Long id, Model model) {
        Optional<Product> product = productService.findOneById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }
        return "product_info";
    }
    @PostMapping("/products/{id}")
    public String searchProductInfo(@RequestParam(defaultValue = "0") Long id, Model model) {
        productService.findOneById(id).ifPresent(p -> model.addAttribute("product", p));
        return "product_info";
    }

    @GetMapping("/products/add")
    public String showCreator() {
        return "add_product_form";
    }
    @PostMapping("/products/add")
    public String createNewProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/";
    }

    @GetMapping("/products/{id}/price/inc")
    public String incrementProductPrice(@PathVariable Long id) {
        productService.incrementPriceById(id, 10);
        return "redirect:/page";
    }

    @GetMapping("/products/{id}/price/dec")
    public String decrementProductPrice(@PathVariable Long id) {
        productService.decrementPriceById(id, 10);
        return "redirect:/page";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/products/sort")
    public String showProductsWherePriceBetween(Model model,
                                                @RequestParam(name = "min", defaultValue = "0") int min,
                                                @RequestParam(name = "max") int max,
                                                @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        Page<Product> page = productService.findAllByPriceBetween(min, max, pageIndex-1, 4);
        model.addAttribute("page", page);
        return "index";
    }

    @GetMapping("/products/titlesearch")
    public String showProductsByPartOfTitle(Model model,
                                                @RequestParam(name = "title") String title,
                                                @RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        Page<Product> page = productService.findAllByTitleLike("%" + title + "%", pageIndex - 1, 4);
        model.addAttribute("page", page);
        return "index";
    }
}
