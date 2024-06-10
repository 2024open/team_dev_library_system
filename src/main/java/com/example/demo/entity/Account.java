package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	private String nickname;

	private String email;

	private String password;

	private Integer privilege;

	private Boolean ban;

	private Boolean deleted;

	public Account() {
	}

<<<<<<< HEAD
	public Account(String userName, String nickname, String email, String password, Integer privilege, Boolean ban,
			Boolean deleted) {
		this.userName = userName;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.privilege = privilege;
		this.ban = ban;
		this.deleted = deleted;
	}
	
//	ゲッターセッター
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickname() {		
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
=======
	public Account(String userName, String nickname, String email, String password) {
		this.userName = userName;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

>>>>>>> branch 'master' of git@github.com:2024open/team_dev_library_system.git
}
