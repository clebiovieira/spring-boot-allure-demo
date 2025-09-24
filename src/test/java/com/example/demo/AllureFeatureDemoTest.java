package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.AllureReportUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This test class demonstrates various Allure reporting features.
 */
@Epic("Allure Features")
@Feature("Allure Reporting")
public class AllureFeatureDemoTest {

    private final ProductService productService = new ProductService();

    @Test
    @DisplayName("Demonstrate Allure steps and attachments")
    @Description("This test demonstrates how to use Allure steps and attachments")
    @Severity(SeverityLevel.NORMAL)
    @Story("Allure Steps Demo")
    @Issue("DEMO-123")
    @TmsLink("TC-456")
    @Link(name = "Documentation", url = "https://docs.qameta.io/allure/")
    public void demonstrateAllureFeatures() {
        AllureReportUtils.logTestStart("Allure Features Demo");
        
        // Step 1: Get a product
        AllureReportUtils.logStep("Step 1", "Retrieving a product from the service");
        Product product = productService.getProductById(1L);
        assertNotNull(product);
        
        // Attach product details
        AllureReportUtils.attachJson("Product Details", 
                String.format("{\"id\":%d,\"name\":\"%s\",\"price\":%.2f}", 
                        product.getId(), product.getName(), product.getPrice()));
        
        // Step 2: Modify the product
        AllureReportUtils.logStep("Step 2", "Modifying the product");
        String originalName = product.getName();
        product.setName("Modified " + originalName);
        
        // Step 3: Update the product
        AllureReportUtils.logStep("Step 3", "Updating the product in the service");
        Product updatedProduct = productService.updateProduct(product.getId(), product);
        assertNotNull(updatedProduct);
        assertEquals("Modified " + originalName, updatedProduct.getName());
        
        // Attach comparison
        AllureReportUtils.attachText("Product Comparison", 
                String.format("Original: %s\nUpdated: %s", originalName, updatedProduct.getName()));
        
        // Step 4: Verify the update
        AllureReportUtils.logStep("Step 4", "Verifying the product was updated");
        Product retrievedProduct = productService.getProductById(product.getId());
        assertEquals(updatedProduct.getName(), retrievedProduct.getName());
        
        // Log test completion
        AllureReportUtils.logTestEnd("Allure Features Demo");
    }
    
    @Test
    @DisplayName("Demonstrate Allure parameters")
    @Description("This test demonstrates how to use Allure parameters")
    @Severity(SeverityLevel.MINOR)
    @Story("Allure Parameters Demo")
    public void demonstrateAllureParameters() {
        AllureReportUtils.logTestStart("Allure Parameters Demo");
        
        // Create a new product with parameters
        String productName = "Parameter Test Product";
        double productPrice = 299.99;
        String productDescription = "Product created for parameter demonstration";
        
        // Log parameters
        Allure.parameter("Product Name", productName);
        Allure.parameter("Product Price", productPrice);
        Allure.parameter("Product Description", productDescription);
        
        // Create the product
        AllureReportUtils.logStep("Creating product with parameters");
        Product product = new Product(null, productName, productPrice, productDescription);
        Product createdProduct = productService.createProduct(product);
        
        // Verify creation
        assertNotNull(createdProduct);
        assertEquals(productName, createdProduct.getName());
        assertEquals(productPrice, createdProduct.getPrice());
        
        AllureReportUtils.logTestEnd("Allure Parameters Demo");
    }
}