package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Library;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.LibraryRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class SuperUserService {
	//リポジトリ
	@Autowired
	LibraryRepository libraryRepository;

	//セッション刑
	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	public void setSuLibraryInfo(Integer libraryId) {
		Library library = libraryRepository.findById(libraryId).get();
		superUser.setLibraryId(library.getLibraryId());
		superUser.setLibraryName(library.getLibraryName());
	}

}
