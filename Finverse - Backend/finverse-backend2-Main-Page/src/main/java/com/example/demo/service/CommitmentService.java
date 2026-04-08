//done
package com.example.demo.service;

import com.example.demo.model.Commitment;
import com.example.demo.repository.CommitmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommitmentService {

    @Autowired
    private CommitmentRepository repo;

    // ============================================
    // ✅ GET USER COMMITMENTS
    // ============================================
    public List<Commitment> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // ============================================
    // ✅ ADD COMMITMENT
    // ============================================
    public Commitment save(Commitment c) {

        if (c.getDueDay() < 1 || c.getDueDay() > 31) {
            throw new RuntimeException("Due day must be between 1 and 31");
        }

        if (c.getAmount() <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        return repo.save(c);
    }

    // ============================================
    // ✅ DELETE COMMITMENT
    // ============================================
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ============================================
    // ✅ GET BY ID (optional but useful)
    // ============================================
    public Commitment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Commitment not found"));
    }
}