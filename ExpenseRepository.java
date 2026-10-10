package com.vcube.expensetracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vcube.expensetracker.model.Expense67;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense67, Long> {

    List<Expense67> findByUser_Id(Long userId);

    @Query("SELECT MONTH(e.date), SUM(e.amount) " +
           "FROM Expense67 e " +
           "WHERE e.user.id = :userId " +
           "GROUP BY MONTH(e.date)")
    List<Object[]> getMonthlyExpense(Long userId);

    @Query("SELECT e.category, SUM(e.amount) " +
           "FROM Expense67 e " +
           "WHERE e.user.id = :userId " +
           "GROUP BY e.category")
    List<Object[]> getCategoryExpense(Long userId);
}
