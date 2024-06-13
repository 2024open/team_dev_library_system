package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendItemDetail;

public interface ALLUserLendDetailRepoitory extends JpaRepository<LendItemDetail, Integer> {
	
	//本詳細表示
	static String sqlALLUserLendItemJoinBookDetail = "SELECT lend_item.lend_item_id,lend_item.library_id, book.title,book.author,book.publisher,book.genre_id,lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN book "
			+ "ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item.lend_Item_id=:lendId;";

	@Query(value = sqlALLUserLendItemJoinBookDetail, nativeQuery = true)
	List<LendItemDetail> sqlALLUserBookLendJoinDetail(@Param("lendId") Integer lendId);

	//CD詳細表示
	static String sqlALLUserLendItemJoinCDDetail = "SELECT lend_item.lend_item_id,lend_item.library_id, cd.title,cd.author,cd.publisher,cd.genre_id,lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN cd "
			+ "ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item.lend_Item_id=:lendId;";

	@Query(value = sqlALLUserLendItemJoinCDDetail, nativeQuery = true)
	List<LendItemDetail> sqlALLUserCDLendJoinDetail(@Param("lendId") Integer lendId);

	//DVD詳細表示
	static String sqlALLUserLendItemJoinDVDDetail = "SELECT lend_item.lend_item_id,lend_item.library_id, dvd.title,dvd.author,dvd.publisher,dvd.genre_id,lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN dvd "
			+ "ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item.lend_Item_id=:lendId;";

	@Query(value = sqlALLUserLendItemJoinDVDDetail, nativeQuery = true)
	List<LendItemDetail> sqlALLUserDVDLendJoinDetail(@Param("lendId") Integer lendId);

	//紙芝居詳細表示
	static String sqlALLUserLendItemJoinKamishibaiDetail = "SELECT lend_item.lend_item_id,lend_item.library_id, kamishibai.title,kamishibai.author,kamishibai.publisher,kamishibai.genre_id,lend_item.status_id,lend_item.category_id  "
			+ "FROM lend_item "
			+ "INNER JOIN kamishibai "
			+ "ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item.lend_Item_id=:lendId;";

	@Query(value = sqlALLUserLendItemJoinKamishibaiDetail, nativeQuery = true)
	List<LendItemDetail> sqlALLUserKamishibaiLendJoinDetail(@Param("lendId") Integer lendId);
}
