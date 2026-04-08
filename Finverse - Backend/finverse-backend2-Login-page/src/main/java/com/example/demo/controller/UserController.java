package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // ── REGISTER ──
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> req) {

        String email = req.get("email");

        if (repo.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email already exists"));
        }

        User user = new User();
        user.setFirstName(req.get("first_name"));
        user.setLastName(req.get("last_name"));
        user.setEmail(email);
        user.setPassword(encoder.encode(req.get("password")));
        user.setRole("USER");

        repo.save(user);

        return ResponseEntity.ok(
                Map.of("message", "Account created successfully")
        );
    }

    // ── LOGIN ──
 // ── LOGIN ──
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {

        var userOpt = repo.findByEmail(req.get("email"));

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }

        User user = userOpt.get();

        if (!encoder.matches(req.get("password"), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid credentials"));
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Login successful",
                        "id", user.getId(),        // ✅ FIXED
                        "email", user.getEmail(),
                        "role", user.getRole()    // ✅ FIXED
                )
        );
    }
    // ── ADMIN LOGIN ──
 // ── ADMIN LOGIN ──
    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> req) {

        var userOpt = repo.findByEmail(req.get("email"));

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }

        User user = userOpt.get();

        if (!encoder.matches(req.get("password"), user.getPassword())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid credentials"));
        }

        if (!"ADMIN".equals(user.getRole())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Not an admin account"));
        }

        return ResponseEntity.ok(
                Map.of(
                        "message", "Admin login successful",
                        "id", user.getId(),        // ✅ FIXED (was userId)
                        "email", user.getEmail(),
                        "role", user.getRole()
                )
        );
    }

}