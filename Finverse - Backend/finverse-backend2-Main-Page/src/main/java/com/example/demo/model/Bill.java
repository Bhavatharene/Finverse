//done
package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;        // e.g., Electricity, Netflix
    private double amount;

    private LocalDate dueDate;

    private boolean paid;

    public Bill() {}

    // Getters and Setters

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public LocalDate getDueDate() { return dueDate; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public boolean isPaid() { return paid; }

    public void setPaid(boolean paid) { this.paid = paid; }
}