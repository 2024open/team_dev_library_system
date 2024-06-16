package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	//カテゴリ更新用
	static String sqlRoomAll = "SELECT * "
			+ "FROM room "
			+ "WHERE deleted = false "
			+ "ORDER BY room_id ; ";
	
	@Query(value = sqlRoomAll, nativeQuery = true)
	List<Room> sqlRoomAll();
}
