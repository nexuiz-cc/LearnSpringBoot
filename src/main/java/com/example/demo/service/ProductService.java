package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        Integer lastId = productRepository.findTopByOrderByIdDesc().getProductId();
        if (lastId != null) {
            product.setProductId(lastId + 1);
        } else {
            product.setProductId(1); // 如果表中没有数据，则设置为 1
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        product.setSize(productDetails.getSize());
        product.setColor(productDetails.getColor());
        product.setPrice(productDetails.getPrice());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setDescription(productDetails.getDescription());
        product.setBrand(productDetails.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}