//done
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String category;

    private double amount;

    private LocalDate date;

    public Income() {}

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }
}