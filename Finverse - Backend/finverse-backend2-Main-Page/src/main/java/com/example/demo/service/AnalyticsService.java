//done
package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    @Autowired private IncomeRepository incomeRepo;
    @Autowired private ExpenseRepository expenseRepo;
    @Autowired private CommitmentRepository commitRepo;
    @Autowired private UserRepository userRepo;

    // ============================================
    // ✅ USER DASHBOARD ANALYTICS
    // ============================================
    public AnalyticsDTO getDashboardAnalytics(Long userId) {

        double totalIncome = incomeRepo.findByUserId(userId)
                .stream().mapToDouble(Income::getAmount).sum();

        double totalExpense = expenseRepo.findByUserId(userId)
                .stream().mapToDouble(Expense::getAmount).sum();

        return new AnalyticsDTO(totalIncome, totalExpense);
    }

    // ============================================
    // ✅ FULL ANALYTICS (FOR SCORE + INTELLIGENCE)
    // ============================================
    public AnalyticsDTO getFullAnalytics(Long userId) {

        double totalIncome = incomeRepo.findByUserId(userId)
                .stream().mapToDouble(Income::getAmount).sum();

        double totalExpense = expenseRepo.findByUserId(userId)
                .stream().mapToDouble(Expense::getAmount).sum();

        double totalCommitment = commitRepo.findByUserId(userId)
                .stream().mapToDouble(Commitment::getAmount).sum();

        double savingsRate = totalIncome == 0 ? 0 :
                ((totalIncome - totalExpense) / totalIncome) * 100;

        return new AnalyticsDTO(
                totalIncome,
                totalExpense,
                totalCommitment,
                savingsRate
        );
    }

    // ============================================
    // ✅ ADMIN ANALYTICS
    // ============================================
    public AdminAnalyticsDTO getAdminAnalytics() {

        double totalIncome = incomeRepo.findAll()
                .stream().mapToDouble(Income::getAmount).sum();

        double totalExpense = expenseRepo.findAll()
                .stream().mapToDouble(Expense::getAmount).sum();

        long users = userRepo.count();
        long transactions = incomeRepo.count() + expenseRepo.count();

        return new AdminAnalyticsDTO(users, totalIncome, totalExpense, transactions);
    }

    // ============================================
    // ✅ SIMULATION (WHAT-IF)
    // ============================================
    public SimulationResponseDTO simulate(SimulationRequest req) {

        Long userId = req.getUserId();

        double currentIncome = incomeRepo.findByUserId(userId)
                .stream().mapToDouble(Income::getAmount).sum();

        double currentExpense = expenseRepo.findByUserId(userId)
                .stream().mapToDouble(Expense::getAmount).sum();

        double newIncome = currentIncome * (1 + req.getIncomeChangePercent() / 100);
        double newExpense = currentExpense * (1 + req.getExpenseChangePercent() / 100);

        return new SimulationResponseDTO(newIncome, newExpense);
    }

    // ============================================
    // ✅ SUBSCRIPTION DETECTION
    // ============================================
    public List<String> detectSubscriptions(Long userId) {

        return expenseRepo.findByUserId(userId)
                .stream()
                .map(Expense::getCategory)
                .filter(category -> {
                    String c = category.toLowerCase();
                    return c.contains("netflix") ||
                           c.contains("spotify") ||
                           c.contains("subscription") ||
                           c.contains("prime") ||
                           c.contains("hotstar");
                })
                .distinct()
                .toList();
    }

    // ============================================
    // ✅ FINANCIAL SCORE
    // ============================================
    public int calculateScore(Long userId) {

        AnalyticsDTO data = getFullAnalytics(userId);

        double income = data.getTotalIncome();
        double expense = data.getTotalExpense();
        double commitment = data.getTotalCommitment();
        double savingsRate = data.getSavingsRate();

        int score = 100;

        if (expense > income) score -= 30;
        if (savingsRate < 10) score -= 20;
        if (commitment > income * 0.5) score -= 20;

        return Math.max(score, 0);
    }
}