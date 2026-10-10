package com.vcube.expensetracker.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcube.expensetracker.model.User67;

@Repository
public interface UserRepository extends JpaRepository<User67, Long> {

	Optional<User67> findByEmail(String email);

}