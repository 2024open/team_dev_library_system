package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DetailIf;

public interface DetailIfRepository extends JpaRepository<DetailIf, Integer> {
	List<DetailIf> findByLendItemId(Integer LendId);
}
