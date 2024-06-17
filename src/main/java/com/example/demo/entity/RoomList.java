package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lend_item")
@Data
public class RoomList {
	
	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "room_name")
	private String roomName;
	
	@Column(name = "status_id")
	private Integer statusId;
	
	@Column(name = "library_id")
	private Integer libraryId;

	public RoomList() {
	}
}
