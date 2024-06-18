package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	static String sqlDeletedFalse = "SELECT * "
			+ "FROM book "
			+ "WHERE deleted = false ;";

	static String sqlBookAll = "SELECT * "
			+ "FROM book "
			+ "WHERE deleted = false "
			+ "ORDER BY book_id ; ";

	@Query(value = sqlBookAll, nativeQuery = true)
	List<Book> sqlBookAll();

	@Query(value = sqlDeletedFalse, nativeQuery = true)
	List<Book> sqlDeletedFalse();
}
