package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public ProductService() {
        // Initialize with some sample products
        createProduct(new Product(null, "Laptop", 1299.99, "High-performance laptop"));
        createProduct(new Product(null, "Smartphone", 799.99, "Latest smartphone model"));
        createProduct(new Product(null, "Headphones", 199.99, "Noise-cancelling headphones"));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public Product createProduct(Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(Long id, Product product) {
        if (!products.containsKey(id)) {
            return null;
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }

    public boolean deleteProduct(Long id) {
        if (!products.containsKey(id)) {
            return false;
        }
        products.remove(id);
        return true;
    }
}