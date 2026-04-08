//done
package com.example.demo.controller;

import com.example.demo.model.Income;
import com.example.demo.model.Expense;
import com.example.demo.repository.IncomeRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private IncomeRepository incomeRepo;

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private UserRepository userRepo;

    // ✅ TOTAL USERS
    @GetMapping("/users/count")
    public Map<String, Long> getUserCount() {
        long count = userRepo.count();
        return Map.of("totalUsers", count);
    }

    // ✅ TOTAL INCOME
    @GetMapping("/income/total")
    public Map<String, Double> getTotalIncome() {
        double total = incomeRepo.findAll()
                .stream()
                .mapToDouble(Income::getAmount)
                .sum();

        return Map.of("totalIncome", total);
    }

    // ✅ TOTAL EXPENSE
    @GetMapping("/expense/total")
    public Map<String, Double> getTotalExpense() {
        double total = expenseRepo.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        return Map.of("totalExpense", total);
    }

    // ✅ ALL TRANSACTIONS (ADMIN TABLE)
    @GetMapping("/transactions")
    public Map<String, Object> getAllTransactions() {

        List<Income> incomes = incomeRepo.findAll();
        List<Expense> expenses = expenseRepo.findAll();

        List<Map<String, Object>> all = new ArrayList<>();

        for (Income i : incomes) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "income");
            map.put("amount", i.getAmount());
            map.put("category", i.getCategory());
            map.put("userId", i.getUserId());
            map.put("date", i.getDate());
            all.add(map);
        }

        for (Expense e : expenses) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", "expense");
            map.put("amount", e.getAmount());
            map.put("category", e.getCategory());
            map.put("userId", e.getUserId());
            map.put("date", e.getDate());
            all.add(map);
        }

        return Map.of("transactions", all);
    }
}