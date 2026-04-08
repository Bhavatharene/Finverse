//done
package com.example.demo.service;

import com.example.demo.model.Income;
import com.example.demo.repository.IncomeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository repo;

    // ============================================
    // ✅ GET USER INCOME
    // ============================================
    public List<Income> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // ============================================
    // ✅ GET ALL INCOME (ADMIN)
    // ============================================
    public List<Income> getAll() {
        return repo.findAll();
    }

    // ============================================
    // ✅ ADD INCOME
    // ============================================
    public Income save(Income i) {

        if (i.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        if (i.getDate() == null) {
            i.setDate(LocalDate.now());
        }

        return repo.save(i);
    }

    // ============================================
    // ✅ DELETE INCOME
    // ============================================
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ============================================
    // ✅ GET BY ID (optional)
    // ============================================
    public Income getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Income not found"));
    }
}