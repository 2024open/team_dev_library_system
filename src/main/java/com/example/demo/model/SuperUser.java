package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class SuperUser {

	private String libraryName;

	public SuperUser() {
	}

	public SuperUser(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
}
