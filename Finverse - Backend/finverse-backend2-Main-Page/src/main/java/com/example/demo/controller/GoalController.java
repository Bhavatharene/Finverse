//done
package com.example.demo.controller;

import com.example.demo.model.Goal;
import com.example.demo.service.GoalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin("*")
public class GoalController {

    @Autowired
    private GoalService service;

    // ============================================
    // ✅ GET USER GOALS
    // ============================================
    @GetMapping
    public List<Goal> get(@RequestParam Long userId) {
        return service.getByUser(userId);
    }

    // ============================================
    // ✅ ADD GOAL
    // ============================================
    @PostMapping
    public Goal add(@RequestBody Goal g) {
        return service.save(g);
    }

    // ============================================
    // ✅ DELETE GOAL
    // ============================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ============================================
    // ✅ ADD SAVINGS TO GOAL
    // ============================================
    @PutMapping("/{id}/add")
    public Goal addSavings(@PathVariable Long id, @RequestParam double amount) {
        return service.updateSavings(id, amount);
    }
}