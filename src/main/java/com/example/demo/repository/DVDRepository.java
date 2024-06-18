package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.DVD;

public interface DVDRepository extends JpaRepository<DVD, Integer> {

	static String sqlDVDAll = "SELECT * "
			+ "FROM dvd "
			+ "WHERE deleted = false "
			+ "ORDER BY dvd_id ; ";

	@Query(value = sqlDVDAll, nativeQuery = true)
	List<DVD> sqlDVDAll();

}
