package com.example.demo.repository;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value ="SELECT * FROM products ORDER BY product_id DESC LIMIT 1", nativeQuery = true)
    Product findTopByOrderByIdDesc();
}
