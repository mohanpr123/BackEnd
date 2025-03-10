package com.example.Authentication.services.ProdServ;

import com.example.Authentication.dto.ProductRequest;
import com.example.Authentication.entity.Product;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.ProductRepository;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  JwtUtil jwtUtil;


    //CREATE
    @Override
    public ProductRequest createProduct(String token, ProductRequest productRequest) {
        User user = jwtUtil.getAuthenticatedUser(token);

        Product product = new Product(productRequest.getName(), productRequest.getPrice(), productRequest.getQuantity(), user, productRequest.getSupplier());
        Product savedProduct = productRepository.save(product);

        return new ProductRequest(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), savedProduct.getQuantity(), savedProduct.getUser().getId(), savedProduct.getSupplier());
    }

    //GET ALL
    @Override
    public Page<ProductRequest> getAllProducts(String token, int page, int size, String sortBy, String direction) {
        User user = jwtUtil.getAuthenticatedUser(token);

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.findByUserAndDeletedFalse(user, pageable);

        return productPage.map(p -> new ProductRequest(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getUser().getId(),
                p.getSupplier()
        ));
    }

    //UPDATE PRODUCT
    @Override
    public ProductRequest updateProduct(String token, Long id, ProductRequest productRequest) {
        User user = jwtUtil.getAuthenticatedUser(token);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this product");
        }

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setSupplier(productRequest.getSupplier());

        Product updatedProduct = productRepository.save(product);

        return new ProductRequest(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getQuantity(),
                updatedProduct.getUser().getId(),
                updatedProduct.getSupplier()
        );
    }


    //DELETE
    @Override
    public void deleteProduct(String token, Long id) {
        User user = jwtUtil.getAuthenticatedUser(token);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (!product.getUser().equals(user)) {
            throw new RuntimeException("Unauthorized to delete this product");
        }

        product.setDeleted(true);
        productRepository.save(product);
    }

    // SEARCH
    @Override
    public Page<ProductRequest> searchProducts(String token, String keyword, int page, int size, String sortBy, String direction)  {
        User user = jwtUtil.getAuthenticatedUser(token);

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.searchProducts(user, keyword, pageable);

        return productPage.map(p -> new ProductRequest(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getUser().getId(),
                p.getSupplier()
        ));
    }
}
