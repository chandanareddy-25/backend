package com.vcube.expensetracker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vcube.expensetracker.model.User67;
import com.vcube.expensetracker.repo.UserRepository;
import com.vcube.expensetracker.service.ExpenseService;

@Controller
public class ReportController {

    @Autowired
    private ExpenseService service;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/reports")
    public String reports(Model model, Principal principal) {

        User67 user = userRepo.findByEmail(principal.getName()).get();

        List<Object[]> monthly = service.getMonthlyReport(user.getId());
        List<Object[]> category = service.getCategoryReport(user.getId());

        model.addAttribute("monthlyReport", monthly);
        model.addAttribute("categoryReport", category);

        return "reports";
    }
}