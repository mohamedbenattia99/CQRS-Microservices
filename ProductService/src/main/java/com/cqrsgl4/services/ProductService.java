package com.cqrsgl4.services;

import com.cqrsgl4.data.Product;
import com.cqrsgl4.data.ProductRepository;
import com.cqrsgl4.events.ProductCreatedEvent;
import com.cqrsgl4.senders.CreateProductSender;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CreateProductSender createProductSender;

    public ProductService(ProductRepository productRepository, CreateProductSender createProductSender
    ) {
        this.productRepository = productRepository;
        this.createProductSender = createProductSender;
        
    }

    public List<Product> getProducts(){
        return this.productRepository.findAll();
    }

    public String createProduct(Product product) throws JsonProcessingException {
        

        Product createdProduct = this.productRepository.save(product);
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity()
        );
        this.createProductSender.send(productCreatedEvent);
        return createdProduct.getName() + " has been created!";
    }


    public List<Product> purgeDb() {
        this.productRepository.findAll().forEach(this.productRepository::delete);
        return this.productRepository.findAll();
    }
}