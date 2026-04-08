//done
package com.example.demo.dto;

public class SimulationRequest {

    private Long userId;

    private double incomeChangePercent;   // e.g. +20 or -10
    private double expenseChangePercent;  // e.g. +15 or -30

    public SimulationRequest() {}

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getIncomeChangePercent() {
        return incomeChangePercent;
    }

    public void setIncomeChangePercent(double incomeChangePercent) {
        this.incomeChangePercent = incomeChangePercent;
    }

    public double getExpenseChangePercent() {
        return expenseChangePercent;
    }

    public void setExpenseChangePercent(double expenseChangePercent) {
        this.expenseChangePercent = expenseChangePercent;
    }
}