package com.vcube.expensetracker.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vcube.expensetracker.model.Category67;
import com.vcube.expensetracker.model.Expense67;
import com.vcube.expensetracker.model.User67;
import com.vcube.expensetracker.repo.UserRepository;
import com.vcube.expensetracker.service.ExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService service;

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		User67 user = userRepo.findByEmail(principal.getName()).get();
		model.addAttribute("expenses", service.getUserExpenses(user.getId()));
		return "dashboard";
	}

	@GetMapping("/add-expense")
	public String addExpenseForm(Model model) {
		model.addAttribute("expense", new Expense67());
		model.addAttribute("categories", service.getAllCategories());
		return "add-expense";
	}

	@PostMapping("/save-expense")
	public String saveExpense(
	        @ModelAttribute Expense67 expense,
	        Principal principal,
	        Model model) {

	    User67 user = userRepo.findByEmail(principal.getName())
	            .orElseThrow();

	    // Set the logged-in user
	    expense.setUser(user);

	    // Validate and load the existing category
	    if (expense.getCategory() == null
	            || expense.getCategory().getId() == null) {

	        model.addAttribute("error", "Please select a category.");
	        model.addAttribute("categories", service.getAllCategories());
	        return "add-expense";
	    }

	    Category67 category =
	            service.getCategoryById(expense.getCategory().getId());

	    expense.setCategory(category);

	    // Save expense with existing user and category
	    service.saveExpense(expense);

	    return "redirect:/dashboard";
	}
}