package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "dvd")
@Data
public class DVD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dvd_id")
	private Integer dvdId;

	private String title;

	private String author;

	private String publisher;

	@Column(name = "genre_id")
	private Integer genreId;

	private Boolean deleted;

	public DVD() {
	}

}
