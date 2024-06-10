package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Account;



public interface AccountRepository extends JpaRepository<Account, Integer> {
	List<Account> findByEmailAndPassword(String email, String password);
	List<Account> findByEmail(String email);
}
