package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.RoomList;

public interface ALLUserLendListRoomRepoitory extends JpaRepository<RoomList, Integer> {

	//本一覧表示
	static String sqlALLUserLendItemJoinRoom = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN room "
			+ "ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinRoom, nativeQuery = true)
	List<RoomList> sqlALLUserRoomLendJoin();


}
