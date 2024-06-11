package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Kamishibai;

public interface KamishibaiRepository extends JpaRepository<Kamishibai, Integer> {

}
