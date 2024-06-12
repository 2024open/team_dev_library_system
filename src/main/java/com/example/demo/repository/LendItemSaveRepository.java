package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.LendItem;



public interface LendItemSaveRepository extends JpaRepository<LendItem, Integer> {
	LendItem findByLendItemId(Integer i);


}
