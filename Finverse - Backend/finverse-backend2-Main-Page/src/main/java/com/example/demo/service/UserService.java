//done
package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    // ============================================
    // ✅ GET ALL USERS (ADMIN)
    // ============================================
    public List<User> getAll() {
        return repo.findAll();
    }

    // ============================================
    // ✅ ADD USER
    // ============================================
    public User save(User user) {

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password required");
        }

        if (user.getRole() == null) {
            user.setRole("USER");
        }

        return repo.save(user);
    }

    // ============================================
    // ✅ DELETE USER
    // ============================================
    public void delete(Long id) {
        repo.deleteById(id);
    }

    // ============================================
    // ✅ GET USER BY ID
    // ============================================
    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ============================================
    // ✅ LOGIN
    // ============================================
    public User login(String email, String password) {

        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}