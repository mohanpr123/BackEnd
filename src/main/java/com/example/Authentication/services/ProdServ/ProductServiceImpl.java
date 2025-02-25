package com.example.Authentication.services.ProdServ;

import com.example.Authentication.dto.ProductDTO;
import com.example.Authentication.entity.Product;
import com.example.Authentication.entity.User;
import com.example.Authentication.repository.ProductRepository;
import com.example.Authentication.repository.UserRepository;
import com.example.Authentication.services.ProdServ.ProductService;
import com.example.Authentication.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    //Extract token
    private User getAuthenticatedUser(String token) {
        String email = jwtUtil.extractEmail(token);
        System.out.println(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    //CREATE
    @Override
    public ProductDTO createProduct(String token, ProductDTO productDTO) {
        User user = getAuthenticatedUser(token);

        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getQuantity(), user,productDTO.getSupplier());
        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice(), savedProduct.getQuantity(), savedProduct.getUser().getId(), savedProduct.getSupplier());
    }

    //GET ALL
    @Override
    public Page<ProductDTO> getAllProducts(String token, int page, int size) {
        User user = getAuthenticatedUser(token);

        // Create Pageable object with page number and size (5 items per page)
        Pageable pageable = PageRequest.of(page, size);

        // Fetch paginated results from repository
        Page<Product> productPage = productRepository.findByUser(user, pageable);

        // Map to DTO
        return productPage.map(p -> new ProductDTO(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getQuantity(),
                p.getUser().getId(),
                p.getSupplier()
        ));
    }

    //GET BY ID
    @Override
    public ProductDTO getProductById(String token, Long productId) {
        User user = getAuthenticatedUser(token);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ensure the product belongs to the logged-in user
        if (!product.getUser().equals(user)) {
            throw new RuntimeException("Unauthorized to access this product");
        }

        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getQuantity(), product.getUser().getId(),product.getSupplier());
    }

    //UPDATE PRODUCT
    @Override
    public ProductDTO updateProduct(String token, Long id, ProductDTO productDTO) {
        User user = getAuthenticatedUser(token);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        System.out.println("Product id for update"+product);

        //Product to login user
        if (!product.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized to update this product");
        }

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setSupplier(productDTO.getSupplier());

        Product updatedProduct = productRepository.save(product);

        return new ProductDTO(
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
        User user = getAuthenticatedUser(token);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Ensuring the product belongs to the logged-in user
        if (!product.getUser().equals(user)) {
            throw new RuntimeException("Unauthorized to delete this product");
        }   

        productRepository.deleteById(id);
    }
}
