package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.DVD;

public interface DVDRepository extends JpaRepository<DVD, Integer> {

}
