package com.vcube.expensetracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.expensetracker.model.Category67;

@Repository
public interface CategoryRepository extends JpaRepository<Category67, Long> {

}
