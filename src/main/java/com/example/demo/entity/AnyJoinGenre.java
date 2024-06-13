package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AnyJoinGenre {

	@Id
	@Column(name = "any_id")
	private Integer anyId;

	private String title;

	private String author;

	private String publisher;

	@Column(name = "genre_id")
	private Integer genreId;

	private Boolean deleted;

	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "category_Id")
	private Integer categoryId;

	public AnyJoinGenre() {
	}

}
