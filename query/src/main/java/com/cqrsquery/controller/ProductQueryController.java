package com.cqrsquery.controller;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/query")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }