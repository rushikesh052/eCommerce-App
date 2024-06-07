package com.rushi.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rushi.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
