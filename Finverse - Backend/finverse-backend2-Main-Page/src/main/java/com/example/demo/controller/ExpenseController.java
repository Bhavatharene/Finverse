//done
package com.example.demo.controller;

import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    // ============================================
    // ✅ GET USER EXPENSES
    // ============================================
    @GetMapping
    public List<Expense> get(@RequestParam Long userId) {
        return service.getByUser(userId);
    }

    // ============================================
    // ✅ ADMIN - GET ALL EXPENSES
    // ============================================
    @GetMapping("/all")
    public List<Expense> getAll() {
        return service.getAll();
    }

    // ============================================
    // ✅ ADD EXPENSE
    // ============================================
    @PostMapping
    public Expense add(@RequestBody Expense e) {
        return service.save(e);
    }

    // ============================================
    // ✅ DELETE EXPENSE
    // ============================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}