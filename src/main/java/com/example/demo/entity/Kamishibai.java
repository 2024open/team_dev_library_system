package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "kamishibai")
@Data
public class Kamishibai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "kamishibai_id")
	private Integer kamishibaiId;

	private String title;

	private String author;

	private String publisher;

	@Column(name = "genre_id")
	private Integer genreId;

	private Boolean deleted;

	public Kamishibai() {
	}

}
