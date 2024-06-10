package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AdminLendList {

	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;

	private String title;

	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "status_name")
	private Integer statusName;
}
