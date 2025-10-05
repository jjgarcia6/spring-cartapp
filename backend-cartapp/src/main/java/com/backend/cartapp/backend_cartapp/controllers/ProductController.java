package com.backend.cartapp.backend_cartapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.backend.cartapp.backend_cartapp.services.ProductService;
import com.backend.cartapp.backend_cartapp.models.entities.Product;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<Product> list() {
        return service.findAll();
    }
}

