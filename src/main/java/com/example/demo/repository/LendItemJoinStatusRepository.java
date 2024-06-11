package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendItemJoinStatus;

public interface LendItemJoinStatusRepository extends JpaRepository<LendItemJoinStatus, Integer> {
	public static String sqlLendProcess = "SELECT lend_item.* , status.status_name "
			+ "FROM lend_item JOIN status ON lend_item.status_id = status.status_id "
			+ "WHERE lend_item_id = :lendItemId ;";

	//貸出処理検索用
	@Query(value = sqlLendProcess, nativeQuery = true)
	List<LendItemJoinStatus> sqlLendProcess(@Param("lendItemId") Integer lendItemId);
}
