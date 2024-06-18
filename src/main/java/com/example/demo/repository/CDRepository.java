package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.CD;

public interface CDRepository extends JpaRepository<CD, Integer> {
	static String sqlCDAll = "SELECT * "
			+ "FROM cd "
			+ "WHERE deleted = false "
			+ "ORDER BY cd_id ; ";

	@Query(value = sqlCDAll, nativeQuery = true)
	List<CD> sqlCDAll();
}
