package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminLendRoom {
	@Id
	@Column(name = "lend_item_id")
	public Integer lendItemId;

	@Column(name = "room_name")
	public String roomName;

	@Column(name = "status_name")
	private String statusName;

}
