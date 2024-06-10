package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AllUserLendList {

	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	private String title;

	private String author;
	
	@Column(name = "genre_id")
	private Integer genreId;
	
	@Column(name = "status_id")
	private Integer statusId;
}
