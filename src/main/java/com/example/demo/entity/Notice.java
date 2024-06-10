package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "notice")
@Data
public class Notice {

	@Id
	@GeneratedValue(strategy = GenrationType.IDENTITY)
	@Column(name = "notice_id")
	private Integer noticeId;

	@Column(name = "library_id")
	private Integer libraryId;

	@Column(name = "user_id")
	private Integer userId;

	private String title;

	private String content;

	@Column(name = "notice_date")
	private Date noticeDate;
}
