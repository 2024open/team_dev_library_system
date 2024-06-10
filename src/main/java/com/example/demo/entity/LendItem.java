package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lend_item")
@Data
public class LendItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lend_item_id")
	private Integer LendItemId;

	@Column(name = "library_id")
	private Integer libraryid;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "any_id")
	private Integer anyId;

	private Boolean deleted;
}
