package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "lend_item")
@Data
public class LendItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lend_item_id")
	private Integer lendItemId;

	@Column(name = "library_id")
	private Integer libraryid;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "create_date")
	@Transient
	private LocalDateTime createDate;

	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column(name = "status_id")
	private Integer statusId;

	@Column(name = "any_id")
	private Integer anyId;

	@Transient
	private Boolean deleted;

	
	
	public LendItem() {
	}
	
	public LendItem(Integer lendItemId, Integer libraryid, Integer categoryId, LocalDateTime updateDate,
			Integer statusId, Integer anyId) {
		super();
		this.lendItemId = lendItemId;
		this.libraryid = libraryid;
		this.categoryId = categoryId;
		this.updateDate = updateDate;
		this.statusId = statusId;
		this.anyId = anyId;
	}





	

}
