package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ReservationRoomDetail {
	
	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "room_name")
	public String roomName;

	@Column(name = "reservation_date")
	private Date reservationDate;
	
}
