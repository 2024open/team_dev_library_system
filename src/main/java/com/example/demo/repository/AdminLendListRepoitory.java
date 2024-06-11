package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AdminLendList;

public interface AdminLendListRepoitory extends JpaRepository<AdminLendList, Integer> {
	static String sqlAdminLendJoinBook = "SELECT lend_item_id, title, genre_name, status_name "
			+ "FROM lend_item "
			+ "JOIN status "
			+ "ON lend_item.status_id = status.status_id "
			+ "JOIN (book JOIN genre ON book.genre_id = genre.genre_id) "
			+ "ON lend_item.category_id = 1 AND any_id = book_id "
			+ "WHERE library_id = :libraryId "
			+ "ORDER BY lend_item_id;";
	static String sqlAdminLendJoinCD = "SELECT lend_item_id, title, genre_name, status_name "
			+ "FROM lend_item "
			+ "JOIN status "
			+ "ON lend_item.status_id = status.status_id "
			+ "JOIN (cd JOIN genre ON cd.genre_id = genre.genre_id) "
			+ "ON lend_item.category_id = 2 AND any_id = cd_id "
			+ "WHERE library_id = :libraryId "
			+ "ORDER BY lend_item_id;";
	static String sqlAdminLendJoinDVD = "SELECT lend_item_id, title, genre_name, status_name "
			+ "FROM lend_item "
			+ "JOIN status "
			+ "ON lend_item.status_id = status.status_id "
			+ "JOIN (dvd JOIN genre ON dvd.genre_id = genre.genre_id) "
			+ "ON lend_item.category_id = 3 AND any_id = dvd_id "
			+ "WHERE library_id = :libraryId "
			+ "ORDER BY lend_item_id;";
	static String sqlAdminLendJoinKamishibai = "SELECT lend_item_id, title, genre_name, status_name "
			+ "FROM lend_item "
			+ "JOIN status "
			+ "ON lend_item.status_id = status.status_id "
			+ "JOIN (kamishibai JOIN genre ON kamishibai.genre_id = genre.genre_id) "
			+ "ON lend_item.category_id = 4 AND any_id = kamishibai_id "
			+ "WHERE library_id = :libraryId "
			+ "ORDER BY lend_item_id;";

	@Query(value = sqlAdminLendJoinBook, nativeQuery = true)
	List<AdminLendList> sqlAdminLendJoinBook(@Param("libraryId") Integer libraryId);

	@Query(value = sqlAdminLendJoinCD, nativeQuery = true)
	List<AdminLendList> sqlAdminLendJoinCD(@Param("libraryId") Integer libraryId);

	@Query(value = sqlAdminLendJoinDVD, nativeQuery = true)
	List<AdminLendList> sqlAdminLendJoinDVD(@Param("libraryId") Integer libraryId);

	@Query(value = sqlAdminLendJoinKamishibai, nativeQuery = true)
	List<AdminLendList> sqlAdminLendJoinKamishibai(@Param("libraryId") Integer libraryId);

}
