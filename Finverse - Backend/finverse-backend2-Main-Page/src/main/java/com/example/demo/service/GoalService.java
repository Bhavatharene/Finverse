//done
package com.example.demo.service;

import com.example.demo.model.Goal;
import com.example.demo.repository.GoalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository repo;

    // ============================================
    // ✅ GET USER GOALS
    // ============================================
    public List<Goal> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    // ============================================
    // ✅ ADD GOAL
    // ============================================
    public Goal save(Goal g) {

        if (g.getTargetAmount() <= 0) {
            throw new RuntimeException("Target must be greater than 0");
        }

        if (g.getMonths() <= 0) {
            throw new RuntimeException("Months must be greater than 0");
        }

        if (g.getSavedAmount() < 0) {
            g.setSavedAmount(0);
        }

        return repo.save(g);
    }

    // ============================================
    // ✅ DELETE GOAL
    // ============================================
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ============================================
    // ✅ UPDATE SAVINGS
    // ============================================
    public Goal updateSavings(Long id, double amount) {

        Goal goal = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Goal not found"));

        goal.setSavedAmount(goal.getSavedAmount() + amount);

        return repo.save(goal);
    }
}