//done
package com.example.demo.service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    // ============================================
    // ✅ GET USER EXPENSES
    // ============================================
    public List<Expense> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // ============================================
    // ✅ GET ALL (ADMIN)
    // ============================================
    public List<Expense> getAll() {
        return repo.findAll();
    }

    // ============================================
    // ✅ ADD EXPENSE
    // ============================================
    public Expense save(Expense e) {

        if (e.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        if (e.getDate() == null) {
            e.setDate(LocalDate.now());
        }

        return repo.save(e);
    }

    // ============================================
    // ✅ DELETE EXPENSE
    // ============================================
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ============================================
    // ✅ GET BY ID (optional)
    // ============================================
    public Expense getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
}