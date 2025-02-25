package com.example.Authentication.services.ProdServ;

import com.example.Authentication.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDTO createProduct(String token, ProductDTO productDTO);
    Page<ProductDTO> getAllProducts(String token, int page, int size);
    ProductDTO getProductById(String token, Long productId);
    ProductDTO updateProduct(String token, Long id, ProductDTO productDTO);
    void deleteProduct(String token, Long id);
}
