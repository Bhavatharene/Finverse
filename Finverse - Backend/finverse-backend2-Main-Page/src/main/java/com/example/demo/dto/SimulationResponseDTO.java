//done
package com.example.demo.dto;

public class SimulationResponseDTO {

    private double projectedIncome;
    private double projectedExpense;
    private double balance;
    private String verdict;

    public SimulationResponseDTO() {}

    public SimulationResponseDTO(double projectedIncome, double projectedExpense) {
        this.projectedIncome = projectedIncome;
        this.projectedExpense = projectedExpense;
        this.balance = projectedIncome - projectedExpense;

        if (balance > 0) {
            this.verdict = "Surplus";
        } else if (balance < 0) {
            this.verdict = "Deficit";
        } else {
            this.verdict = "Break-even";
        }
    }

    public double getProjectedIncome() {
        return projectedIncome;
    }

    public void setProjectedIncome(double projectedIncome) {
        this.projectedIncome = projectedIncome;
    }

    public double getProjectedExpense() {
        return projectedExpense;
    }

    public void setProjectedExpense(double projectedExpense) {
        this.projectedExpense = projectedExpense;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}