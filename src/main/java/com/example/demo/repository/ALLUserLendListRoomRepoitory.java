package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.RoomList;

public interface ALLUserLendListRoomRepoitory extends JpaRepository<RoomList, Integer> {

	//部屋一覧表示
	static String sqlALLUserLendItemJoinRoom = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN room "
			+ "ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinRoom, nativeQuery = true)
	List<RoomList> sqlALLUserRoomLendJoin();
	
	
	//部屋一覧表示
	static String sqlALLUserLendItemJoinRoomSearch = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN room "
			+ "ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 and room.room_name LIKE :search "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinRoomSearch, nativeQuery = true)
	List<RoomList> sqlALLUserRoomLendJoinSearch(@Param("search") String search);
	
	//貸会議室・貸出可
	static String sqlALLUserLendItemJoinRoomP = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN room "
			+ "ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 AND lend_item.status_id=1 "
			+ "order by lend_item.lend_item_id asc;";

	@Query(value = sqlALLUserLendItemJoinRoomP, nativeQuery = true)
	List<RoomList> sqlALLUserRoomLendJoinP();
	
	
	//貸会議室・貸出不可
		static String sqlALLUserLendItemJoinRoomI = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
				+ "FROM lend_item "
				+ "INNER JOIN room "
				+ "ON lend_item.any_id = room.room_id "
				+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 AND lend_item.status_id=3 "
				+ "order by lend_item.lend_item_id asc;";

		@Query(value = sqlALLUserLendItemJoinRoomI, nativeQuery = true)
		List<RoomList> sqlALLUserRoomLendJoinI();
	
	
	//部屋一覧表示・検索・貸出可
		static String sqlALLUserLendItemJoinRoomSearchP = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
				+ "FROM lend_item "
				+ "INNER JOIN room "
				+ "ON lend_item.any_id = room.room_id "
				+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 and room.room_name LIKE :search AND lend_item.status_id=1 "
				+ "order by lend_item.lend_item_id asc;";

		@Query(value = sqlALLUserLendItemJoinRoomSearchP, nativeQuery = true)
		List<RoomList> sqlALLUserRoomLendJoinSearchP(@Param("search") String search);
		
		
		//部屋一覧表示・検索・貸出不可
		static String sqlALLUserLendItemJoinRoomSearchI = "SELECT lend_item.lend_item_id,lend_item.library_id,room.room_name, lend_item.status_id,lend_item.category_id "
				+ "FROM lend_item "
				+ "INNER JOIN room "
				+ "ON lend_item.any_id = room.room_id "
				+ "WHERE lend_item.category_id=5 AND lend_item.library_id=1 and room.room_name LIKE :search AND lend_item.status_id=3 "
				+ "order by lend_item.lend_item_id asc;";

		@Query(value = sqlALLUserLendItemJoinRoomSearchI, nativeQuery = true)
		List<RoomList> sqlALLUserRoomLendJoinSearchI(@Param("search") String search);
		
		


}
