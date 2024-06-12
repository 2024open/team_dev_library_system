package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "lending_item")
@Data
public class LendingItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lending_item_id")
	private Integer lendingItemId;

	@Column(name = "lend_item_id")
	private Integer lendItemId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "borrowed_date")
	private LocalDate borrowedDate;

	@Column(name = "status_id")
	private Integer statusId;

	@Transient
	private Boolean deleted;

	public LendingItem() {
	}

}
