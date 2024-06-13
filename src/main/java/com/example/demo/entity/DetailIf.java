package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "lend_item")
@Data
public class DetailIf {
	
	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
	@Column(name = "category_id")
	private Integer categoryId;
	
	
}
