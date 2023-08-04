package com.example.products.controller;

import com.example.products.model.Product;
import com.example.products.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct(1L, "ABC1234567", "Product 1", 50.0, "Description 1", true));
        productList.add(createProduct(2L, "XYZ9876543", "Product 2", 75.0, "Description 2", false));

        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        verify(productService, times(1)).getAllProducts();
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().size() == 2;
    }

    @Test
    public void testGetProductById() {
        long productId = 1L;
        Product product = createProduct(1L,"ABC1234567", "Example Product", 100.0, "Description", true);

        when(productService.getProductById(productId)).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.getProductById(productId);

        verify(productService, times(1)).getProductById(productId);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() == productId;
    }

    @Test
    public void testCreateProduct() {
        Product product = createProduct(1L, "ABC1234567", "Example Product", 100.0, "Description", true);

        when(productService.createProduct(any())).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        verify(productService, times(1)).createProduct(any());
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() == 1L;
    }

    @Test
    public void testUpdateProduct() {
        long productId = 1L;
        Product product = createProduct(productId, "ABC1234567", "Example Product", 100.0, "Description", true);

        when(productService.updateProduct(eq(productId), any())).thenReturn(product);

        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, product);

        verify(productService, times(1)).updateProduct(eq(productId), any());
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody() != null;
        assert responseEntity.getBody().getId() == productId;
    }

    @Test
    public void testDeleteProduct() {
        long productId = 1L;
        doNothing().when(productService).deleteProduct(productId);

        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

        verify(productService, times(1)).deleteProduct(productId);
        assert responseEntity.getStatusCode() == HttpStatus.OK;
        assert responseEntity.getBody().equals("Product with ID " + productId + " deleted successfully.");
    }

    private Product createProduct(Long id, String code, String name, double priceEur, String description, boolean isAvailable) {
        Product product = new Product();
        product.setId(id);
        product.setCode(code);
        product.setName(name);
        product.setPriceEur(priceEur);
        product.setDescription(description);
        product.setAvailable(isAvailable);
        return product;
    }
}
