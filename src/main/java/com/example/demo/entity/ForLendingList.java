package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

//貸出中一覧で使用
@Entity
@Data
public class ForLendingList {

	//LendingItem
	@Id
	@Column(name = "lending_item_id")
	private Integer lendingItemId;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "borrowed_date")
	private LocalDate borrowedDate;

	@Column(name = "status_id")
	private Integer statusId;

	//LendItem
	@Column(name = "lend_item_id")
	private Integer lendItemId;

	@Column(name = "library_id")
	private Integer libraryid;

	@Column(name = "category_id")
	private Integer categoryId;

	@Column(name = "any_id")
	private Integer anyId;

	//category
	@Column(name = "category_name")
	private String categoryName;

	//Book
	@Column(name = "book_title")
	private String bookTitle;

	//CD
	@Column(name = "cd_title")
	private String cdTitle;

	//DVD
	@Column(name = "dvd_title")
	private String dvdTitle;

	//kamishibai
	@Column(name = "kamishibai_title")
	private String kamishibaiTitle;

	//Room
	@Column(name = "room_name")
	private String roomName;

	public ForLendingList() {
	}

}
