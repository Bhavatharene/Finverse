package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // INCOME / EXPENSE / COMMIT

    @Column(name = "user_id")
    private Long userId; // null for global

    @Column(nullable = false)
    private boolean global;

    // ============================================
    // ✅ AUTO-FIX BEFORE SAVING (VERY IMPORTANT)
    // Prevents invalid data
    // ============================================
    @PrePersist
    @PreUpdate
    public void validateCategory() {
        if (this.userId == null) {
            this.global = true;
        } else {
            this.global = false;
        }

        // Normalize type to uppercase
        if (this.type != null) {
            this.type = this.type.toUpperCase();
        }
    }

    // ============================================
    // GETTERS & SETTERS
    // ============================================

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }
}