package ru.johnnygomezzz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void incrementPriceById(Long id, int amount) {
        Product p = productRepository.findById(id).get();
        p.incrementPrice(amount);
    }

    @Transactional
    public void decrementPriceById(Long id, int amount) {
        Product p = productRepository.findById(id).get();
        p.decrementPrice(amount);
    }

    public Page<Product> findAllByPriceBetween(int min, int max, int page, int size) {
        return productRepository.findAllByPriceBetween(min, max, PageRequest.of(page, size));
    }

    public Page<Product> findAllByTitleLike(String title, int page, int size) {
        return productRepository.findAllByTitleLike(title, PageRequest.of(page, size));
    }
}

