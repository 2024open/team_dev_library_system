package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LendItemRoomDetail {

	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "status_id")
	private Integer statusId;
	
	@Column(name = "library_id")
	private Integer libraryId;
	
}
