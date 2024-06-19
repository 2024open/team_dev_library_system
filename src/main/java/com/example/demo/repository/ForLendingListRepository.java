package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ForLendingList;

public interface ForLendingListRepository extends JpaRepository<ForLendingList, Integer> {
	//貸出中一覧 status_id = 2
	static String sqlForLendingList = "SELECT lending_item.lending_item_id, lending_item.return_date, lending_item.borrowed_date, lending_item.status_id, "
			+ "x.* , "
			+ "c.category_name "
			+ "FROM lending_item "
			+ "JOIN "
			+ "(SELECT le.lend_item_id, le.library_id, le.category_id, le.any_id, "
			+ "b.title AS book_title, "
			+ "c.title AS cd_title, "
			+ "d.title AS dvd_title, "
			+ "k.title AS kamishibai_title, "
			+ "r.room_name AS room_name "
			+ "FROM lend_item le "
			+ "LEFT JOIN book b ON le.any_id = b.book_id AND le.category_id = 1 "
			+ "LEFT JOIN cd c ON le.any_id = c.cd_id AND le.category_id = 2 "
			+ "LEFT JOIN dvd d ON le.any_id = d.dvd_id AND le.category_id = 3 "
			+ "LEFT JOIN kamishibai k ON le.any_id = k.kamishibai_id AND le.category_id = 4 "
			+ "LEFT JOIN room r ON le.any_id = r.room_id AND le.category_id = 5) x "
			+ "ON lending_item.lend_item_Id = x.lend_item_id "
			+ "JOIN category c "
			+ "ON x.category_id = c.category_id "
			+ "WHERE lending_item.user_id = :userId "
			+ "AND status_id = 2 "
			+ "AND lending_item.deleted = false "
			+ "ORDER BY borrowed_date DESC ;";

	//貸出履歴
	static String sqlForLendingHistory = "SELECT lending_item.lending_item_id, lending_item.return_date, lending_item.borrowed_date, lending_item.status_id, "
			+ "x.* , "
			+ "c.category_name "
			+ "FROM lending_item "
			+ "JOIN "
			+ "(SELECT le.lend_item_id, le.library_id, le.category_id, le.any_id, "
			+ "b.title AS book_title, "
			+ "c.title AS cd_title, "
			+ "d.title AS dvd_title, "
			+ "k.title AS kamishibai_title, "
			+ "r.room_name AS room_name "
			+ "FROM lend_item le "
			+ "LEFT JOIN book b ON le.any_id = b.book_id AND le.category_id = 1 "
			+ "LEFT JOIN cd c ON le.any_id = c.cd_id AND le.category_id = 2 "
			+ "LEFT JOIN dvd d ON le.any_id = d.dvd_id AND le.category_id = 3 "
			+ "LEFT JOIN kamishibai k ON le.any_id = k.kamishibai_id AND le.category_id = 4 "
			+ "LEFT JOIN room r ON le.any_id = r.room_id AND le.category_id = 5) x "
			+ "ON lending_item.lend_item_Id = x.lend_item_id "
			+ "JOIN category c "
			+ "ON x.category_id = c.category_id "
			+ "WHERE lending_item.user_id = :userId "
			+ "AND lending_item.deleted = false "
			+ "ORDER BY borrowed_date DESC ;";

	@Query(value = sqlForLendingList, nativeQuery = true)
	List<ForLendingList> sqlForLendingList(@Param("userId") Integer userId);

	@Query(value = sqlForLendingHistory, nativeQuery = true)
	List<ForLendingList> sqlForLendingHistory(@Param("userId") Integer userId);

}
