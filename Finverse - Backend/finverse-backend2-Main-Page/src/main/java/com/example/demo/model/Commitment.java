//done
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Commitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String category;
    private double amount;

    private int dueDay; // 1–31 (monthly recurring)

    private String description;

    public Commitment() {}

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public int getDueDay() { return dueDay; }

    public void setDueDay(int dueDay) { this.dueDay = dueDay; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}