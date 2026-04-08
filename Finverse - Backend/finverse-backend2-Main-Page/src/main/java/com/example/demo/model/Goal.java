//done
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name;

    private double targetAmount;

    private int months;

    private double savedAmount; // optional tracking

    public Goal() {}

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getTargetAmount() { return targetAmount; }

    public void setTargetAmount(double targetAmount) { this.targetAmount = targetAmount; }

    public int getMonths() { return months; }

    public void setMonths(int months) { this.months = months; }

    public double getSavedAmount() { return savedAmount; }

    public void setSavedAmount(double savedAmount) { this.savedAmount = savedAmount; }
}