package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "cd")
@Data
public class CD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cd_id")
	private Integer cdId;

	private String title;

	private String author;

	private String publisher;

	@Column(name = "genre_id")
	private Integer genreId;

	@Transient
	private Boolean deleted;

	public CD() {
	}

}
