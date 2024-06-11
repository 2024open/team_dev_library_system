package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LendItem;

public interface LendItemRepository extends JpaRepository<LendItem, Integer> {

}
