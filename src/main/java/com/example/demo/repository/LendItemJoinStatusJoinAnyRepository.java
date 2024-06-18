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
	//ID検索用SQL
	//使わない 06/13
	//使用　06/18
	static String sqlLendProcessBookId = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN book ON lend_item.any_id = book.book_id "
			+ "WHERE lend_item_id = :lendItemId "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false ";
	static String sqlLendProcessCDId = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN cd ON lend_item.any_id = cd.cd_id "
			+ "WHERE lend_item_id = :lendItemId "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false ";
	static String sqlLendProcessDVDId = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN dvd ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE lend_item_id = :lendItemId "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false ";
	static String sqlLendProcessKamishibaiId = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN kamishibai ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE lend_item_id = :lendItemId "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false ";
	static String sqlLendProcessRoomId = "SELECT lend_item.* , status.status_name, room_name AS title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN room ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item_id = :lendItemId "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false ";

	//キーワード検索用SQL
	static String sqlLendProcessBookKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN book ON lend_item.any_id = book.book_id "
			+ "WHERE category_id = 1 "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessCDKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN cd ON lend_item.any_id = cd.cd_id "
			+ "WHERE category_id = 2 "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessDVDKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN dvd ON lend_item.any_id = dvd.dvd_id "
			+ "WHERE category_id = 3 "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessKamishibaiKeyword = "SELECT lend_item.* , status.status_name, title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN kamishibai ON lend_item.any_id = kamishibai.kamishibai_id "
			+ "WHERE category_id = 4 "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false "
			+ "AND title LIKE :keyword "
			+ "ORDER BY lend_item_id; ";
	static String sqlLendProcessRoomKeyword = "SELECT lend_item.* , status.status_name, room_name AS title "
			+ "FROM lend_item "
			+ "JOIN status ON lend_item.status_id = status.status_id "
			+ "JOIN room ON lend_item.any_id = room.room_id "
			+ "WHERE category_id = 5 "
			+ "AND library_id = :libraryId "
			+ "AND lend_item.deleted = false "
			+ "AND room_name LIKE :keyword "
			+ "ORDER BY lend_item_id ;";

	//ID検索用
	@Query(value = sqlLendProcessBookId, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessBookId(
			@Param("lendItemId") Integer lendItemId,
			@Param("libraryId") Integer libraryId);

	@Query(value = sqlLendProcessCDId, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessCDId(
			@Param("lendItemId") Integer lendItemId,
			@Param("libraryId") Integer libraryId);

	@Query(value = sqlLendProcessDVDId, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessDVDId(
			@Param("lendItemId") Integer lendItemId,
			@Param("libraryId") Integer libraryId);

	@Query(value = sqlLendProcessKamishibaiId, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessKamishibaiId(
			@Param("lendItemId") Integer lendItemId,
			@Param("libraryId") Integer libraryId);

	@Query(value = sqlLendProcessRoomId, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessRoomId(
			@Param("lendItemId") Integer lendItemId,
			@Param("libraryId") Integer libraryId);

	//キーワード検索用クエリ
	@Query(value = sqlLendProcessBookKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessBookKeyword(
			@Param("libraryId") Integer libraryId,
			@Param("keyword") String keyword);

	@Query(value = sqlLendProcessCDKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessCDKeyword(
			@Param("libraryId") Integer libraryId,
			@Param("keyword") String keyword);

	@Query(value = sqlLendProcessDVDKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessDVDKeyword(
			@Param("libraryId") Integer libraryId,
			@Param("keyword") String keyword);

	@Query(value = sqlLendProcessKamishibaiKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessKamishibaiKeyword(
			@Param("libraryId") Integer libraryId,
			@Param("keyword") String keyword);

	@Query(value = sqlLendProcessRoomKeyword, nativeQuery = true)
	List<LendItemJoinStatusJoinAny> sqlLendProcessRoomKeyword(
			@Param("libraryId") Integer libraryId,
			@Param("keyword") String keyword);
}
