package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendItemStatus;

public interface ALLUserLendListStatusRepoitory extends JpaRepository<LendItemStatus, Integer> {

	//紙芝居貸出不可・検索
	static String sqlALLUserLendItemStatus = "select lend_item.lend_item_id "
			+ "FROM lend_item "
			+ "inner join reservation "
			+ "on lend_item.lend_item_id = reservation.lend_item_id "
			+ "where reservation.reservation_id = :reservationId;";

	@Query(value = sqlALLUserLendItemStatus, nativeQuery = true)
	LendItemStatus sqlALLUserLendItemStatus(@Param("reservationId") Integer reservationId);
}
