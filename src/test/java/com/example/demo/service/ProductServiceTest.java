package com.example.demo.service;

import com.example.demo.model.Product;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Product Management")
@Feature("Product Service")
public class ProductServiceTest {

    private final ProductService productService = new ProductService();

    @Test
    @DisplayName("Should return all products")
    @Description("This test verifies that the service returns all products correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("List Products")
    public void testGetAllProducts() {
        // When
        List<Product> products = productService.getAllProducts();
        
        // Then
        assertNotNull(products);
        assertEquals(3, products.size());
        
        // Attach data to the report
        Allure.addAttachment("All Products", "application/json", products.toString());
    }

    @Test
    @DisplayName("Should return product by ID")
    @Description("This test verifies that the service returns a product by its ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Product")
    public void testGetProductById() {
        // Given
        Long productId = 1L;
        
        // When
        Product product = productService.getProductById(productId);
        
        // Then
        assertNotNull(product);
        assertEquals(productId, product.getId());
        assertEquals("Laptop", product.getName());
    }

    @Test
    @DisplayName("Should create a new product")
    @Description("This test verifies that the service creates a new product correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create Product")
    public void testCreateProduct() {
        // Given
        Product newProduct = new Product(null, "Tablet", 499.99, "New tablet model");
        
        // When
        Product createdProduct = productService.createProduct(newProduct);
        
        // Then
        assertNotNull(createdProduct);
        assertNotNull(createdProduct.getId());
        assertEquals("Tablet", createdProduct.getName());
        assertEquals(499.99, createdProduct.getPrice());
        
        // Verify it was added to the list
        Product retrievedProduct = productService.getProductById(createdProduct.getId());
        assertNotNull(retrievedProduct);
        assertEquals(createdProduct.getId(), retrievedProduct.getId());
    }

    @Test
    @DisplayName("Should update an existing product")
    @Description("This test verifies that the service updates an existing product correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Update Product")
    public void testUpdateProduct() {
        // Given
        Long productId = 2L;
        Product updatedProduct = new Product(productId, "Updated Smartphone", 899.99, "Updated description");
        
        // When
        Product result = productService.updateProduct(productId, updatedProduct);
        
        // Then
        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Updated Smartphone", result.getName());
        assertEquals(899.99, result.getPrice());
        
        // Verify it was updated in the list
        Product retrievedProduct = productService.getProductById(productId);
        assertEquals("Updated Smartphone", retrievedProduct.getName());
    }

    @Test
    @DisplayName("Should delete an existing product")
    @Description("This test verifies that the service deletes an existing product correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Delete Product")
    public void testDeleteProduct() {
        // Given
        Long productId = 3L;
        
        // When
        boolean deleted = productService.deleteProduct(productId);
        
        // Then
        assertTrue(deleted);
        assertNull(productService.getProductById(productId));
    }

    @Test
    @DisplayName("Should return null when product not found")
    @Description("This test verifies that the service returns null when a product is not found")
    @Severity(SeverityLevel.MINOR)
    @Story("Get Product")
    public void testGetProductByIdNotFound() {
        // Given
        Long nonExistentId = 999L;
        
        // When
        Product product = productService.getProductById(nonExistentId);
        
        // Then
        assertNull(product);
    }
}