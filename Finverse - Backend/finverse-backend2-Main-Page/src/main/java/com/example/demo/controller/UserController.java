//done
package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    // ============================================
    // ✅ GET ALL USERS (ADMIN PANEL)
    // ============================================
    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    // ============================================
    // ✅ ADD USER (ADMIN)
    // ============================================
    @PostMapping
    public User add(@RequestBody User user) {
        return service.save(user);
    }

    // ============================================
    // ✅ DELETE USER
    // ============================================
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ============================================
    // ✅ LOGIN API
    // ============================================
    @PostMapping("/login")
    public User login(@RequestBody Map<String, String> req) {

        String email = req.get("email");
        String password = req.get("password");

        return service.login(email, password);
    }
}