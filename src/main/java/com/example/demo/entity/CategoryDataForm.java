package com.example.demo.entity;

import lombok.Data;

@Data
public class CategoryDataForm {
	
	private String title;
	
	private String author;
	
	private String publisher;
	
	private Integer genreId;
}
