package com.example.products.service;

import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ExchangeRateService exchangeRateService;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(createProduct(1L, "ABC1234567", "Product 1", 50.0, "Description 1", true));
        productList.add(createProduct(2L, "XYZ9876543", "Product 2", 75.0, "Description 2", false));

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> retrievedProducts = productService.getAllProducts();

        Assertions.assertEquals(2, retrievedProducts.size());
        Assertions.assertEquals("Product 1", retrievedProducts.get(0).getName());
        Assertions.assertEquals("Product 2", retrievedProducts.get(1).getName());
    }

    @Test
    public void testGetProductById() {
        long productId = 1L;
        Product product = createProduct(productId, "ABC1234567", "Example Product", 100.0, "Description", true);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.getProductById(productId);

        Assertions.assertNotNull(retrievedProduct);
        Assertions.assertEquals("Example Product", retrievedProduct.getName());
    }

    @Test
    public void testGetProductByIdNotFound() {
        long productId = 5L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product retrievedProduct = productService.getProductById(productId);

        Assertions.assertNull(retrievedProduct);
    }

    @Test
    public void testCreateProduct() {
        Product productToCreate = createProduct(1L, "ABC1234567", "New Product", 50.0, "New Description", true);
        double mockExchangeRate = 1.0985;

        when(exchangeRateService.fetchUsdExchangeRate()).thenReturn(mockExchangeRate);
        when(productRepository.save(any(Product.class))).thenReturn(productToCreate);

        Product createdProduct = productService.createProduct(productToCreate);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertNotNull(createdProduct.getId());
        Assertions.assertEquals("New Product", createdProduct.getName());
        Assertions.assertEquals(50.0, createdProduct.getPriceEur());
        Assertions.assertEquals(54.93, createdProduct.getPriceUsd(), 0.01);
    }

    @Test
    public void testUpdateProduct() {
        long productId = 1L;
        Product existingProduct = createProduct(productId, "ABC1234567", "Existing Product", 100.0, "Description", true);
        double mockExchangeRate = 1.0985;

        when(exchangeRateService.fetchUsdExchangeRate()).thenReturn(mockExchangeRate);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(any(Product.class))).thenReturn(existingProduct);

        Product updatedProduct = productService.updateProduct(productId, existingProduct);

        Assertions.assertNotNull(updatedProduct);
        Assertions.assertEquals(productId, updatedProduct.getId());
        Assertions.assertEquals("Existing Product", updatedProduct.getName());
        Assertions.assertEquals(100.0, updatedProduct.getPriceEur());
        Assertions.assertEquals(109.85, updatedProduct.getPriceUsd(), 0.01);
    }

    @Test
    public void testUpdateProductNotFound() {
        long productId = 1L;
        Product nonExistingProduct = createProduct(productId, "ABC1234567", "Non-existing Product", 0.0, "", false);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> productService.updateProduct(productId, nonExistingProduct));
    }

    @Test
    public void testDeleteProduct() {
        long productId = 1L;
        Product existingProduct = createProduct(productId, "ABC1234567", "Product to be deleted", 150.0, "Description", true);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        doNothing().when(productRepository).delete(existingProduct);

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).delete(existingProduct);
    }

    @Test
    public void testDeleteProductNotFound() {
        long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(productId));
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
