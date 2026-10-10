package com.vcube.expensetracker.service;

import java.util.List;

import com.vcube.expensetracker.model.Category67;
import com.vcube.expensetracker.model.Expense67;

public interface ExpenseService {

    List<Expense67> getUserExpenses(Long userId);

    void saveExpense(Expense67 expense);

    List<Object[]> getMonthlyReport(Long userId);

    List<Object[]> getCategoryReport(Long userId);

    List<Category67> getAllCategories();
    
    Category67 getCategoryById(Long id);
}