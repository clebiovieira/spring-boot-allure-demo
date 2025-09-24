package com.example.demo.controller;

import com.example.demo.model.Product;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("Product API")
@Feature("Product REST Controller")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/api/products";
    }

    @Test
    @DisplayName("Should get all products")
    @Description("This test verifies that the API returns all products correctly")
    @Severity(SeverityLevel.BLOCKER)
    @Story("List Products API")
    public void testGetAllProducts() {
        given()
            .filter(new AllureRestAssured())
        .when()
            .get()
        .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(3))
            .body("[0].name", notNullValue())
            .body("[0].price", notNullValue());
    }

    @Test
    @DisplayName("Should get product by ID")
    @Description("This test verifies that the API returns a product by its ID")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Product API")
    public void testGetProductById() {
        given()
            .filter(new AllureRestAssured())
        .when()
            .get("/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", equalTo("Laptop"))
            .body("price", equalTo(1299.99f));
    }

    @Test
    @DisplayName("Should return 404 for non-existent product")
    @Description("This test verifies that the API returns 404 when a product is not found")
    @Severity(SeverityLevel.NORMAL)
    @Story("Get Product API")
    public void testGetProductByIdNotFound() {
        given()
            .filter(new AllureRestAssured())
        .when()
            .get("/999")
        .then()
            .statusCode(404);
    }

    @Test
    @DisplayName("Should create a new product")
    @Description("This test verifies that the API creates a new product correctly")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create Product API")
    public void testCreateProduct() {
        Product newProduct = new Product(null, "Test Product", 99.99, "Test description");

        given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .body(newProduct)
        .when()
            .post()
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("name", equalTo("Test Product"))
            .body("price", equalTo(99.99f))
            .body("description", equalTo("Test description"));
    }

    @Test
    @DisplayName("Should update an existing product")
    @Description("This test verifies that the API updates an existing product correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Update Product API")
    public void testUpdateProduct() {
        Product updatedProduct = new Product(null, "Updated Product", 149.99, "Updated description");

        given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .body(updatedProduct)
        .when()
            .put("/2")
        .then()
            .statusCode(200)
            .body("id", equalTo(2))
            .body("name", equalTo("Updated Product"))
            .body("price", equalTo(149.99f))
            .body("description", equalTo("Updated description"));
    }

    @Test
    @DisplayName("Should delete an existing product")
    @Description("This test verifies that the API deletes an existing product correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Delete Product API")
    public void testDeleteProduct() {
        // First, ensure the product exists
        given()
            .filter(new AllureRestAssured())
        .when()
            .get("/3")
        .then()
            .statusCode(200);

        // Then delete it
        given()
            .filter(new AllureRestAssured())
        .when()
            .delete("/3")
        .then()
            .statusCode(204);

        // Verify it's gone
        given()
            .filter(new AllureRestAssured())
        .when()
            .get("/3")
        .then()
            .statusCode(404);
    }

    @Test
    @DisplayName("Should validate product input")
    @Description("This test verifies that the API validates product input correctly")
    @Severity(SeverityLevel.NORMAL)
    @Story("Create Product API")
    public void testCreateProductValidation() {
        Product invalidProduct = new Product(null, "", -10.0, "Invalid product");

        given()
            .filter(new AllureRestAssured())
            .contentType(ContentType.JSON)
            .body(invalidProduct)
        .when()
            .post()
        .then()
            .statusCode(400);
    }
}