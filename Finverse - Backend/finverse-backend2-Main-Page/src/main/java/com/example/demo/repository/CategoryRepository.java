package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // USER + GLOBAL
    List<Category> findByUserIdOrGlobalTrue(Long userId);

    // GLOBAL ONLY
    List<Category> findByGlobalTrue();

    // USER ONLY
    List<Category> findByUserId(Long userId);

    // SAFE DELETE
    int deleteByIdAndUserId(Long id, Long userId);
}