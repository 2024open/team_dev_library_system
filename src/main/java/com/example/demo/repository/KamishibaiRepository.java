package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Kamishibai;

public interface KamishibaiRepository extends JpaRepository<Kamishibai, Integer> {
	static String sqlKamishibaiAll = "SELECT * "
			+ "FROM kamishibai "
			+ "WHERE deleted = false "
			+ "ORDER BY kamishibai_id ; ";

	@Query(value = sqlKamishibaiAll, nativeQuery = true)
	List<Kamishibai> sqlKamishibaiAll();
}
