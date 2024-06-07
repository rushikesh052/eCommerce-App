package com.rushi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
