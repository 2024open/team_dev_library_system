package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LendingItem;

public interface LendingItemRepository extends JpaRepository<LendingItem, Integer> {
	List<LendingItem> findByLendItemId(Integer lendItemId);

}
