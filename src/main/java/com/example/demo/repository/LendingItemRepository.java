package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LendingItem;

public interface LendingItemRepository extends JpaRepository<LendingItem, Integer> {

}
