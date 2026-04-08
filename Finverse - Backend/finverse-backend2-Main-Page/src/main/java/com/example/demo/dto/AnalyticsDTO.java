//done
package com.example.demo.dto;

public class AnalyticsDTO {

    private double totalIncome;
    private double totalExpense;
    private double totalCommitment;
    private double savingsRate;

    // =========================
    // DEFAULT CONSTRUCTOR
    // =========================
    public AnalyticsDTO() {}

    // =========================
    // 2-PARAM CONSTRUCTOR (for simple use)
    // =========================
    public AnalyticsDTO(double totalIncome, double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.totalCommitment = 0;
        this.savingsRate = totalIncome == 0 ? 0 :
                ((totalIncome - totalExpense) / totalIncome) * 100;
    }

    // =========================
    // FULL CONSTRUCTOR
    // =========================
    public AnalyticsDTO(double totalIncome,
                        double totalExpense,
                        double totalCommitment,
                        double savingsRate) {

        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.totalCommitment = totalCommitment;
        this.savingsRate = savingsRate;
    }

    // =========================
    // GETTERS
    // =========================
    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getTotalCommitment() {
        return totalCommitment;
    }

    public double getSavingsRate() {
        return savingsRate;
    }

    // =========================
    // SETTERS
    // =========================
    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public void setTotalCommitment(double totalCommitment) {
        this.totalCommitment = totalCommitment;
    }

    public void setSavingsRate(double savingsRate) {
        this.savingsRate = savingsRate;
    }
}