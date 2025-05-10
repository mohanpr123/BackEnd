package com.example.Authentication.controllers;

import com.example.Authentication.dto.ProductRequest;
import com.example.Authentication.services.ProdServ.ProductService;
import com.example.Authentication.utility.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<ProductRequest> createProduct(@RequestBody ProductRequest productRequest, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        return ResponseEntity.ok(productService.createProduct(token, productRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductRequest>> getProducts(
            HttpServletRequest request,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        String token = jwtUtil.extractToken(request);
        Page<ProductRequest> products;

        if (keyword != null && !keyword.isEmpty()) {
            products = productService.searchProducts(token, keyword, page, size, sortBy, direction);
        } else {
            products = productService.getAllProducts(token, page, size, sortBy, direction);
        }

        return ResponseEntity.ok(products);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductRequest> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        return ResponseEntity.ok(productService.updateProduct(token, id, productRequest));
    }

    // SOFT DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        productService.deleteProduct(token, id);
        return ResponseEntity.noContent().build();
    }
}
