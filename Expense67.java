package com.vcube.expensetracker.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Expense67 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	private Double amount;
	private LocalDate date;
	private String description;
	
	@ManyToOne
	private User67 user;

	@ManyToOne
	private Category67 category;


}
