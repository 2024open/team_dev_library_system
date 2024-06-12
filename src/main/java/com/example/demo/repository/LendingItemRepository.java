package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendingItem;

public interface LendingItemRepository extends JpaRepository<LendingItem, Integer> {
	public static String sqlLendingList = "SELECT * "
			+ "	FROM lending_item "
			+ "	WHERE user_id = :userId AND status_id = 2;";

	public static String sqlLendingHistory = "SELECT * "
			+ "	FROM lending_item "
			+ "	WHERE user_id = :userId ;";

	List<LendingItem> findByLendItemId(Integer lendItemId);

	@Query(value = sqlLendingList, nativeQuery = true)
	List<LendingItem> sqlLendingList(@Param("userId") Integer userId);

	@Query(value = sqlLendingHistory, nativeQuery = true)
	List<LendingItem> sqlLendingHistory(@Param("userId") Integer userId);
}
