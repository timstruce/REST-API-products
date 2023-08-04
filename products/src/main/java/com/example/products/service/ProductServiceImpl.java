package com.example.products.service;

import com.example.products.model.Product;
import com.example.products.repository.ProductRepository;
import com.example.products.util.DecimalUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements  ProductService{

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Override
    public List<Product> getAllProducts() {
        return this.repository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        return this.repository.findById(productId).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        double priceUsd = product.getPriceEur() * fetchUsdExchangeRate();
        product.setPriceUsd(DecimalUtil.round(priceUsd,2));
        return this.repository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product product) {
        Product productDb = this.repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found for ID: " + productId));

        productDb.setName(product.getName());
        productDb.setPriceEur(product.getPriceEur());
        productDb.setDescription(product.getDescription());
        productDb.setAvailable(product.isAvailable());

        double priceUsd = product.getPriceEur() * fetchUsdExchangeRate();
        productDb.setPriceUsd(DecimalUtil.round(priceUsd,2));

        return this.repository.save(productDb);
    }

    @Override
    public void deleteProduct(long productId) {
        Product productDb = this.repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found for ID: " + productId));
        this.repository.delete(productDb);
    }

    private double fetchUsdExchangeRate() {
        return this.exchangeRateService.fetchUsdExchangeRate();
    }
}
