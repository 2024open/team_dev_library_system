package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ReservationDetail;


public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
	
	//予約一覧表示
		static String sqlReservationList = "select reservation.lend_item_id,lend_item.category_id,book.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join book "
				+ "on lend_item.any_id=book.book_id "
				+ "where reservation.user_id= :UserId ;";

		@Query(value = sqlReservationList, nativeQuery = true)
		List<ReservationDetail> sqlReservationLendJoin(@Param("UserId")Integer UserId);
		
		
}
