package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.example.demo.entity.Genre;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendItemJoinStatus;
import com.example.demo.entity.Library;
import com.example.demo.entity.Status;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CDRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DVDRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.KamishibaiRepository;
import com.example.demo.repository.LendItemJoinStatusRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.StatusRepository;

@Service
public class LibrarianService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	LibraryRepository libraryRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	CDRepository cdRepository;

	@Autowired
	DVDRepository dvdRepository;

	@Autowired
	KamishibaiRepository kamishibaiRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	LendItemJoinStatusRepository lendItemJoinStatusRepository;

	public void forLibraryPullDown(Model model) {
		List<Library> libraryList = libraryRepository.findAll();
		model.addAttribute("libraryList", libraryList);
	}

	public void forCategoryPullDown(Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);
	}

	public void forStatusList(Model model) {
		List<Status> statusList = statusRepository.findAll();
		model.addAttribute("statusList", statusList);
	}

	//検索ためのlibraryIdの保持
	//ヘッダーのための情報
	public void forLibraryId(Model model, Integer libraryId) {
		Library library = libraryRepository.findById(libraryId).get();
		model.addAttribute("libraryId", libraryId);
		model.addAttribute("library", library);
	}

	//貸出物更新用
	public void forLendItemEdit(LendItem lendItem, Model model) {
		switch (lendItem.getCategoryId()) {
		case 1:
			Book book = bookRepository.findById(lendItem.getAnyId()).get();
			model.addAttribute("lendAnyItem", book);
			break;
		}

		List<Genre> genreList = genreRepository.findByCategoryId(lendItem.getCategoryId());
		model.addAttribute("genreList", genreList);
		forStatusList(model);
		model.addAttribute("lendItem", lendItem);
	}

	//貸出処理検索用
	public void forLendProcessSearch(Integer lendItemId, Model model) {
		List<LendItemJoinStatus> tmpList = lendItemJoinStatusRepository.sqlLendProcess(lendItemId);
		if (tmpList.size() == 0) {
			//検索結果なし
		} else {
			LendItemJoinStatus lendItem = tmpList.get(0);
			model.addAttribute("lendItem", lendItem);

			String title = null;
			switch (lendItem.getCategoryId()) {
			case 1:
				title = bookRepository.findById(lendItem.getAnyId()).get().getTitle();
				break;
			case 2:
				title = cdRepository.findById(lendItem.getAnyId()).get().getTitle();
				break;
			case 3:
				title = dvdRepository.findById(lendItem.getAnyId()).get().getTitle();
				break;
			case 4:
				title = kamishibaiRepository.findById(lendItem.getAnyId()).get().getTitle();
				break;
			case 5:
				title = roomRepository.findById(lendItem.getAnyId()).get().getRoomName();
			}
			model.addAttribute("title", title);
		}
	}

}