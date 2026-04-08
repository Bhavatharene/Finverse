//done
package com.example.demo.controller;

import com.example.demo.model.Commitment;
import com.example.demo.service.CommitmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commitments")
@CrossOrigin("*")
public class CommitmentController {

    @Autowired
    private CommitmentService service;

    // ============================================
    // ✅ GET USER COMMITMENTS
    // ============================================
    @GetMapping
    public List<Commitment> get(@RequestParam Long userId) {
        return service.getByUser(userId);
    }

    // ============================================
    // ✅ ADD COMMITMENT
    // ============================================
    @PostMapping
    public Commitment add(@RequestBody Commitment c) {
        return service.save(c);
    }

    // ============================================
    // ✅ DELETE COMMITMENT
    // ============================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}