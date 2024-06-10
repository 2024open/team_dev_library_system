package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	//TODO
	//	SELECT lend_item_id, room_name, status_name 
	//	FROM lend_item
	//		JOIN status 
	//			ON lend_item.status_id = status.status_id
	//		JOIN room
	//			ON lend_item.category_id = 5 AND any_id = room_id
	//	ORDER BY lend_item_id;
}
