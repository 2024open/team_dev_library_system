package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class ReservationDetail {
	
	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	private String title;

	@Column(name = "reservation_date")
	@Transient private Date reservationDate;
	
}
