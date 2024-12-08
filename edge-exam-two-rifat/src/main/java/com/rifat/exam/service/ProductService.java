package com.rifat.exam.service;

import com.rifat.exam.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    // Save a new product
    Product save(Product productInfo);

    // Update an existing product
    Product update(Product productInfo);

    // Delete a product by its ID
    Product delete(Integer id);

    // Get a product by its ID
    Product getById(Integer id);

    // Get all products
    List<Product> getAll() throws SQLException;
}
