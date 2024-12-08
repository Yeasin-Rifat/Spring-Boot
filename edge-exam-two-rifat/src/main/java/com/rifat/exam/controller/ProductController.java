package com.rifat.exam.controller;



import  com.rifat.exam.model.Product;
import  com.rifat.exam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create a new Product
    @PostMapping
    public Product createProduct(@RequestBody Product productInfo) {
        return productService.save(productInfo);
    }

    // Update an existing Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product productInfo) {
        // Set the product ID to the productInfo object
        productInfo.setId(id);
        return productService.update(productInfo);
    }

    // Delete a Product by ID
    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable Integer id) {
        return productService.delete(id);
    }

    // Get all Products
    @GetMapping
    public List<Product> getAllProducts() throws SQLException {
        return productService.getAll();
    }

    // Get a Product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getById(id);
    }
}

