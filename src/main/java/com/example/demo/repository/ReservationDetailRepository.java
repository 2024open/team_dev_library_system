package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ReservationDetail;


public interface ReservationDetailRepository extends JpaRepository<ReservationDetail, Integer> {
	
	//予約一覧表示
		static String sqlReservationBookList = "select reservation.reservation_id, reservation.lend_item_id,lend_item.category_id,book.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join book "
				+ "on lend_item.any_id=book.book_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=1;";

		@Query(value = sqlReservationBookList, nativeQuery = true)
		List<ReservationDetail> sqlReservationBookLendJoin(@Param("UserId")Integer UserId);
		
		static String sqlReservationCDList = "select reservation.reservation_id,reservation.lend_item_id,lend_item.category_id,cd.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join cd "
				+ "on lend_item.any_id=cd.cd_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=2;";

		@Query(value = sqlReservationCDList, nativeQuery = true)
		List<ReservationDetail> sqlReservationCDLendJoin(@Param("UserId")Integer UserId);
		
		
		static String sqlReservationDVDList = "select reservation.reservation_id,reservation.lend_item_id,lend_item.category_id,dvd.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join dvd "
				+ "on lend_item.any_id=dvd.dvd_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=3;";

		@Query(value = sqlReservationDVDList, nativeQuery = true)
		List<ReservationDetail> sqlReservationDVDLendJoin(@Param("UserId")Integer UserId);
		
		
		static String sqlReservationKamishibaiList = "select reservation.reservation_id,reservation.lend_item_id,lend_item.category_id,kamishibai.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join kamishibai "
				+ "on lend_item.any_id=kamishibai.kamishibai_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=4;";

		@Query(value = sqlReservationKamishibaiList, nativeQuery = true)
		List<ReservationDetail> sqlReservationKamishibaiLendJoin(@Param("UserId")Integer UserId);
		
		static String sqlReservationRoomList = "select reservation.lend_item_id,lend_item.category_id,room.room_name,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join room "
				+ "on lend_item.any_id=room.room_id "
				+ "where reservation.user_id= :UserId AND lend_item.category_id=4;";

		@Query(value = sqlReservationRoomList, nativeQuery = true)
		List<ReservationDetail> sqlReservationRoomLendJoin(@Param("UserId")Integer UserId);
		
		
		
		static String sqlReservationList = "select reservation.lend_item_id,lend_item.category_id,book.title,reservation.reservation_date "
				+ "from reservation "
				+ "inner join lend_item "
				+ "on reservation.lend_item_id=lend_item.lend_item_id "
				+ "inner join book "
				+ "on lend_item.any_id=book.book_id "
				+ "inner join cd "
				+ "on lend_item.any_id=cd.cd_id "
				+ "inner join dvd "
				+ "on lend_item.any_id=dvd.dvd_id "
				+ "inner join kamishibai "
				+ "on lend_item.any_id=kamishibai.kamishibai_id "
				+ "inner join room "
				+ "on lend_item.any_id=room.room_id "
				+ "where reservation.user_id= :UserId";

		@Query(value = sqlReservationBookList, nativeQuery = true)
		List<ReservationDetail> sqlReservationLendJoin(@Param("UserId")Integer UserId);
}
