package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService service;

    // 1. GET USER + GLOBAL
    @GetMapping
    public ResponseEntity<List<Category>> getCategories(@RequestParam Long userId) {
        return ResponseEntity.ok(service.getUserAndGlobalCategories(userId));
    }

    // 2. GET GLOBAL ONLY
    @GetMapping("/global")
    public ResponseEntity<List<Category>> getGlobalCategories() {
        return ResponseEntity.ok(service.getGlobalCategories());
    }

    // 3. ADD CATEGORY
    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(service.save(category));
    }

    // 4. DELETE CATEGORY
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable Long id,
            @RequestParam Long userId) {

        // Validate userId
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is required");
        }

        Optional<Category> optional = service.findById(id);

        // Not found
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Category not found");
        }

        Category category = optional.get();

        // Prevent deleting global
        if (category.isGlobal()) {
            return ResponseEntity.badRequest().body("Cannot delete global category");
        }

        // Prevent deleting others
        if (!category.getUserId().equals(userId)) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        // Delete
        service.deleteById(id);

        return ResponseEntity.ok("Deleted successfully");
    }
}