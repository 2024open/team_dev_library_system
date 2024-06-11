package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.Library;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LibraryRepository;

@Service
public class LibrarianService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	BookRepository bookRepository;

	public void forLibraryPullDown(Model model) {
		List<Library> libraryList = libraryRepository.findAll();
		model.addAttribute("libraryList", libraryList);
	}

	public void forCategoryPullDown(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);
	}

	//検索ためのlibraryIdの保持
	//ヘッダーのための情報
	public void forLibraryId(Model model, Integer libraryId) {
		Library library = libraryRepository.findById(libraryId).get();
		model.addAttribute("libraryId", libraryId);
		model.addAttribute("library", library);
	}

	public void forLendItemDetail(LendItem lendItem, Model model) {
		switch (lendItem.getCategoryId()) {
		case 1:
			Book book = bookRepository.findById(lendItem.getAnyId()).get();
			model.addAttribute("LendAnyItem", book);
			break;
		}

		model.addAttribute("lendItem", lendItem);
	}

}
