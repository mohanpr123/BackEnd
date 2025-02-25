package com.example.Authentication.repository;

import com.example.Authentication.entity.Product;
import com.example.Authentication.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByUser(User user, Pageable pageable);
}
