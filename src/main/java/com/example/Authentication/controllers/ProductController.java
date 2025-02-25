package com.example.Authentication.controllers;

import com.example.Authentication.dto.ProductDTO;
import com.example.Authentication.services.ProdServ.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return (header != null && header.startsWith("Bearer ")) ? header.substring(7) : null;
    }

    //CREATE
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO, HttpServletRequest request) {
        String token = extractToken(request);
        return ResponseEntity.ok(productService.createProduct(token, productDTO));
    }

    //GET ALL
    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,    // Page number, defaults to 0 (first page)
            @RequestParam(defaultValue = "5") int size) {  // Items per page, defaults to 5
        String token = extractToken(request);
        Page<ProductDTO> products = productService.getAllProducts(token, page, size);
        return ResponseEntity.ok(products);
    }

    //GET BY ID
//    @GetMapping("/{id}")
//    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id, HttpServletRequest request) {
//        String token = extractToken(request);
//        return ResponseEntity.ok(productService.getProductById(token, id));
//    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO, HttpServletRequest request) {
        String token = extractToken(request);
        return ResponseEntity.ok(productService.updateProduct(token, id, productDTO));
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        String token = extractToken(request);
        productService.deleteProduct(token, id);
        return ResponseEntity.noContent().build();
    }
}
