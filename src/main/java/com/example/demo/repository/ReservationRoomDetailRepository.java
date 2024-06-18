package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ReservationRoomDetail;


public interface ReservationRoomDetailRepository extends JpaRepository<ReservationRoomDetail, Integer> {
	
		static String sqlReservationRoomList = "select  reservation.reservation_id,reservation.lend_item_id,lend_item.category_id,room.room_name,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join room "
				+ "on lend_item.any_id=room.room_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=5;";

		@Query(value = sqlReservationRoomList, nativeQuery = true)
		List<ReservationRoomDetail> sqlReservationRoomLendJoin(@Param("UserId")Integer UserId);

}
