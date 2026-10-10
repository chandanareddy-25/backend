package com.vcube.expensetracker.controller;

import com.vcube.expensetracker.model.User67;
import com.vcube.expensetracker.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User67());
        return "Registration";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User67 user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail() == null ? "" : user.getEmail().trim().toLowerCase();
        if (email.isBlank() || user.getPassword() == null || user.getPassword().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Email and password are required.");
            return "redirect:/register";
        }

        if (userRepository.findByEmail(email).isPresent()) {
            redirectAttributes.addFlashAttribute("errorMessage", "An account with this email already exists. Please log in.");
            return "redirect:/register";
        }

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please log in.");
        return "redirect:/login?registered";
    }
}
