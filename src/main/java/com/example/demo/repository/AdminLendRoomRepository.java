package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.AdminLendRoom;

public interface AdminLendRoomRepository extends JpaRepository<AdminLendRoom, Integer> {
	public static String sqlAdminLendJoinRoom = "SELECT lend_item_id, room_name, status_name "
			+ "FROM lend_item "
			+ "JOIN status "
			+ "ON lend_item.status_id = status.status_id "
			+ "JOIN room "
			+ "ON lend_item.category_id = 5 AND any_id = room_id "
			+ "WHERE library_id = :libraryId "
			+ "ORDER BY lend_item_id;";

	@Query(value = sqlAdminLendJoinRoom, nativeQuery = true)
	List<AdminLendRoom> sqlAdminLendJoinRoom(@Param("libraryId") Integer libraryId);

}
