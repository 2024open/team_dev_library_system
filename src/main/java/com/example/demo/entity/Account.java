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

	//	@Column(name="privilege", columnDefinition="default '2'")
	private Integer privilege;

	//	@Column(name="ban", columnDefinition="default 'false'")
	private Boolean ban;

	//	@Column(name="deleted", columnDefinition="default 'false'")
	private Boolean deleted;

	public Account() {
	}

	//	会員登録用コンストラクタ
	public Account(String userName, String nickname, String email, String password) {

		this.userName = userName;
		this.nickname = nickname;
		this.email = email;
		this.password = password;

	}

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

	public boolean isEmpty() {
		return false;
	}

}
