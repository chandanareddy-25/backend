package com.vcube.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vcube.expensetracker.model.Category67;
import com.vcube.expensetracker.model.Expense67;
import com.vcube.expensetracker.repo.CategoryRepository;
import com.vcube.expensetracker.repo.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Expense67> getUserExpenses(Long userId) {
	    return expenseRepo.findByUser_Id(userId);
	}
	@Override
	public void saveExpense(Expense67 expense) {
		expenseRepo.save(expense);
	}

	@Override
	public List<Object[]> getMonthlyReport(Long userId) {
		return expenseRepo.getMonthlyExpense(userId);
	}

	@Override
	public List<Object[]> getCategoryReport(Long userId) {
		return expenseRepo.getCategoryExpense(userId);
	}

	// ✅ FIXED: return Category objects from DB
	@Override
	public List<Category67> getAllCategories() {
		return categoryRepository.findAll();
	}

	// ✅ Already correct
	@Override
	public Category67 getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow();
	}
}
