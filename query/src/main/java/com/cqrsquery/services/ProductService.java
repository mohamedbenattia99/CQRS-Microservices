package com.cqrsquery.services;

import org.springframework.stereotype.Service;

import java.util.List;

import com.cqrsquery.Data.Product;
import com.cqrsquery.Data.ProductRepository;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public Product getProductByRef(String ref){
            return this.productRepository.findById(ref).orElse(null);
        }

    public void addProduct(Product product){
        this.productRepository.save(product);
    }

    public List<Product> purgeDb() {
        this.productRepository.findAll().forEach(this.productRepository::delete);
        return this.productRepository.findAll();
    }
}