package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "library")
@Data
public class Library {
	@Id
	@Column(name = "library_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer libraryId;

	@Column(name = "library_name")
	private String libraryName;

	@Column(name = "libarary_address")
	private String libraryAddress;

	@Column(name = "library_tel")
	private String libraryTel;

	private Boolean deleted;

}
