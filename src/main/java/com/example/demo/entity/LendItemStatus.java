package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LendItemStatus {

	@Id
	@Column(name = "lend_item_id")
	private Integer lendItemId;
	
}
