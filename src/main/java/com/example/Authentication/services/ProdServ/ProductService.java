package com.example.Authentication.services.ProdServ;

import com.example.Authentication.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    ProductRequest createProduct(String token, ProductRequest productRequest);
    Page<ProductRequest> getAllProducts(String token, int page, int size, String sortBy, String direction);
    ProductRequest updateProduct(String token, Long id, ProductRequest productRequest);
    void deleteProduct(String token, Long id);
    Page<ProductRequest> searchProducts(String token, String keyword, int page, int size, String sortBy, String direction);

}
