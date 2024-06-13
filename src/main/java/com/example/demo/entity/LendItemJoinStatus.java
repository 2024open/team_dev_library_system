package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LendItemJoinStatus {
	@Id
	@Column(name = "lend_item_id")
	private Integer LendItemId;

	@Column(name = "library_id")
	private Integer libraryId;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "create_date")
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "any_id")
	private Integer anyId;

	private Boolean deleted;

	@Column(name = "status_name")
	private String statusName;

	public LendItemJoinStatus() {
	}

}
