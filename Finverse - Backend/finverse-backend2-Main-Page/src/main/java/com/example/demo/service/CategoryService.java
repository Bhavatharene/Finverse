package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    // USER + GLOBAL
    public List<Category> getUserAndGlobalCategories(Long userId) {
        if (userId == null) {
            return repository.findByGlobalTrue();
        }
        return repository.findByUserIdOrGlobalTrue(userId);
    }

    // GLOBAL ONLY
    public List<Category> getGlobalCategories() {
        return repository.findByGlobalTrue();
    }

    // SAVE
    public Category save(Category category) {
        return repository.save(category);
    }

    // FIND BY ID
    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    // DELETE
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // SAFE DELETE (optional usage)
    public boolean deleteUserCategory(Long id, Long userId) {
        return repository.deleteByIdAndUserId(id, userId) > 0;
    }
}