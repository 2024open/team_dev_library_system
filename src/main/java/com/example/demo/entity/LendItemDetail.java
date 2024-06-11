package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LendItemDetail {

	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	private String title;

	private String author;
	
	private String publisher;
	
	@Column(name = "genre_id")
	private Integer genreId;
	
	@Column(name = "status_id")
	private Integer statusId;
	
	@Column(name = "library_id")
	private Integer libraryId;
	
}
