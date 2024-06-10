package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class SQLService {

	//使用不可
	//xxx
	public static String makeAdminLendListSQL(String categoryName, Integer categoryId) {
		String sql;
		sql = "SELECT lend_item_id, title, genre_name, status_name "
				+ "FROM lend_item "
				+ "JOIN status "
				+ "ON lend_item.status_id = status.status_id "
				+ "JOIN (" + categoryName + " JOIN genre ON " + categoryName + ".genre_id = genre.genre_id) "
				+ "ON lend_item.category_id = " + categoryId + " AND any_id = " + categoryName + "_id "
				+ "WHERE library_id = :libraryId "
				+ "ORDER BY lend_item_id;";
		return sql;
	}

}
