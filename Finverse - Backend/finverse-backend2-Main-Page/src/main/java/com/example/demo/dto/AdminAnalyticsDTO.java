//done
package com.example.demo.dto;

public class AdminAnalyticsDTO {

    private long totalUsers;
    private double totalIncome;
    private double totalExpense;
    private long totalTransactions;

    public AdminAnalyticsDTO() {}

    public AdminAnalyticsDTO(long totalUsers, double totalIncome,
                             double totalExpense, long totalTransactions) {
        this.totalUsers = totalUsers;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.totalTransactions = totalTransactions;
    }

    public long getTotalUsers() { return totalUsers; }
    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpense() { return totalExpense; }
    public long getTotalTransactions() { return totalTransactions; }

    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }
    public void setTotalIncome(double totalIncome) { this.totalIncome = totalIncome; }
    public void setTotalExpense(double totalExpense) { this.totalExpense = totalExpense; }
    public void setTotalTransactions(long totalTransactions) { this.totalTransactions = totalTransactions; }
}