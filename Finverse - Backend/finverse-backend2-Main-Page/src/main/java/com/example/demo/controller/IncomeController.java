//done
package com.example.demo.controller;

import com.example.demo.model.Income;
import com.example.demo.service.IncomeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/income")
@CrossOrigin("*")
public class IncomeController {

    @Autowired
    private IncomeService service;

    // ============================================
    // ✅ GET USER INCOME
    // ============================================
    @GetMapping
    public List<Income> get(@RequestParam Long userId) {
        return service.getByUser(userId);
    }

    // ============================================
    // ✅ ADMIN - GET ALL INCOME
    // ============================================
    @GetMapping("/all")
    public List<Income> getAll() {
        return service.getAll();
    }

    // ============================================
    // ✅ ADD INCOME
    // ============================================
    @PostMapping
    public Income add(@RequestBody Income i) {
        return service.save(i);
    }

    // ============================================
    // ✅ DELETE INCOME
    // ============================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}