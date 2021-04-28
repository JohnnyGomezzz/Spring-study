package ru.johnnygomezzz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findOneById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

//    public double getAverageScore() {
//        return productRepository.findAll().stream().mapToInt(Product::getScore).average().getAsDouble();
//    }
//
//    public void increaseScore(Long id) {
//        for (Product s : productRepository.findAll()) {
//            if (s.getId().equals(id) && s.getScore() < 100) {
//                s.setScore(s.getScore() + 1);
//            }
//        }
//    }
//
//    public void decreaseScore(Long id) {
//        for (Product s : productRepository.findAll()) {
//            if (s.getId().equals(id) && s.getScore() > 0) {
//                s.setScore(s.getScore() - 1);
//            }
//        }
//    }
}
