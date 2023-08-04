package com.example.products.service;

import com.example.products.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts ();

    public Product getProductById(long productId);

    public Product createProduct (Product product);

    public Product updateProduct (long productId, Product product);

    public void deleteProduct (long productId);
}
