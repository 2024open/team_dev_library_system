package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendItemJoinStatusJoinAny;

//貸出処理 貸出物キーワード検索
//lend_item status any貸出物
public interface LendItemJoinStatusJoinAnyRepository
		extends JpaRepository<LendItemJoinStatusJoinAny, Integer> {
	static String sqlLendProcessBookKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN book ON lend_item.any_id = book.book_id "
			+ "WHERE category_id = 1 "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessCDKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN cd ON lend_item.any_id = cd.cd_id "
			+ "WHERE category_id = 2 "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessDVDKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN dvd ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE category_id = 3 "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessKamishibaiKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN kamishibai ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE category_id = 4 "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";

	@Query(value = sqlLendProcessBookKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessBookKeyword(@Param("keyword") String keyword);

	@Query(value = sqlLendProcessCDKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessCDKeyword(@Param("keyword") String keyword);

	@Query(value = sqlLendProcessDVDKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessDVDKeyword(@Param("keyword") String keyword);

	@Query(value = sqlLendProcessKamishibaiKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessKamishibaiKeyword(@Param("keyword") String keyword);
}
