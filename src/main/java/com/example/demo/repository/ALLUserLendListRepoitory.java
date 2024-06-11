package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.AllUserLendList;

public interface ALLUserLendListRepoitory extends JpaRepository<AllUserLendList, Integer> {
	
//	//ALL
//	static String sqlALLUserLendJoin = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id "
//			+ "FROM lend_item "
//			+ "INNER JOIN book "
//			+ "ON lend_item.any_id = book.book_id  "
//			+ "WHERE lend_item.library_id=1;";
//	@Query(value = sqlALLUserLendJoin, nativeQuery = true)
//	List<AllUserLendList> sqlALLUserLendJoin();
	
	//本
	static String sqlALLUserBookLendJoinBook = "SELECT lend_item.lend_item_id,lend_item.library_id, book.genre_id, book.title, book.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.category_id=1 AND lend_item.library_id=1;";
	
	@Query(value = sqlALLUserBookLendJoinBook, nativeQuery = true)
	List<AllUserLendList> sqlALLUserBookLendJoin();
	
	//CD
	static String sqlALLUserBookLendJoinCD = "SELECT lend_item.lend_item_id, lend_item.library_id,cd.genre_id, cd.title, cd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.category_id=2 AND lend_item.library_id=1;";

	@Query(value = sqlALLUserBookLendJoinCD, nativeQuery = true)
	List<AllUserLendList> sqlALLUserCDLendJoin();
	
	
	//DVD
	static String sqlALLUserBookLendJoinDVD = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.genre_id, dvd.title, dvd.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.category_id=3 AND lend_item.library_id=1;";

	@Query(value = sqlALLUserBookLendJoinDVD, nativeQuery = true)
	List<AllUserLendList> sqlALLUserDVDLendJoin();
	
	
	//紙芝居
	static String sqlALLUserBookLendJoinKamishibai = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.genre_id, kamishibai.title, kamishibai.author, lend_item.status_id "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.category_id=4  AND lend_item.library_id=1;";

	@Query(value = sqlALLUserBookLendJoinKamishibai, nativeQuery = true)
	List<AllUserLendList> sqlALLUserKamishibaiLendJoin();

}