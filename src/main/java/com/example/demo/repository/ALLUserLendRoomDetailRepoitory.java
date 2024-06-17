package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LendItemRoomDetail;

public interface ALLUserLendRoomDetailRepoitory extends JpaRepository<LendItemRoomDetail, Integer> {
	
	//本詳細表示
	static String sqlALLUserLendItemJoinRoomDetail = "SELECT lend_item.lend_item_id,lend_item.library_id, room.room_name,lend_item.status_id,lend_item.category_id "
			+ "FROM lend_item "
			+ "INNER JOIN room "
			+ "ON lend_item.any_id = room.room_id "
			+ "WHERE lend_item.lend_Item_id=:lendId;";

	@Query(value = sqlALLUserLendItemJoinRoomDetail, nativeQuery = true)
	List<LendItemRoomDetail> sqlALLUserRoomLendJoinDetail(@Param("lendId") Integer lendId);

}
