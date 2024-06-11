package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "notice")
@Data
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notice_id")
	private Integer noticeId;

	@Column(name = "library_id")
	private Integer libraryId;

	@Column(name = "user_id")
	private Integer userId;

	private String title;

	private String content;
	
	// 日付の文字列フォーマット
		private final static DateTimeFormatter FMT = 
			DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
	@Column(name = "notice_date")
	private LocalDateTime noticeDate;

	public Notice() {
	}

//	お知らせ新規登録用
	public Notice(Integer libraryId, Integer userId, String title, String content) {
		this.libraryId = libraryId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.noticeDate = LocalDateTime.now();// 現在日時
	}
//	お知らせ更新用
	public Notice(Integer noticeId, Integer libraryId, Integer userId, String title, String content) {
	
		this.noticeId = noticeId;
		this.libraryId = libraryId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.noticeDate = LocalDateTime.now();// 現在日時
	}
	
	public String getNoticeDate() {
		return noticeDate.format(FMT); // LocalDateTime→String変換
	}
	
	public String getContent() {
		return content.replaceAll("\n", "<br>"); // 改行文字列→<br>置換
	}


	
	
	
}
