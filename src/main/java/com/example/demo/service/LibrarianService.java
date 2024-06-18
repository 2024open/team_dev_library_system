package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.Book;
import com.example.demo.entity.CD;
import com.example.demo.entity.Category;
import com.example.demo.entity.DVD;
import com.example.demo.entity.Kamishibai;
import com.example.demo.entity.Library;
import com.example.demo.entity.Room;
import com.example.demo.entity.Status;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CDRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DVDRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.KamishibaiRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.StatusRepository;

@Service
public class LibrarianService {

	//リポジトリ
	@Autowired
	LendItemRepository lendItemRepository;

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

	public void forLibraryList(Model model) {
		List<Library> libraryList = libraryRepository.findAll();
		model.addAttribute("libraryList", libraryList);
	}

	public void forCategoryList(Model model) {
		List<Category> categoryList = categoryRepository.findByOrderByCategoryId();
		model.addAttribute("categoryList", categoryList);
	}

	public void forStatusList(Model model) {
		List<Status> statusList = statusRepository.findAll();
		model.addAttribute("statusList", statusList);
	}

	//Listに返す
	public void forBookList(Model model) {
		List<Book> bookList = bookRepository.sqlBookAll();
		model.addAttribute("bookList", bookList);
	}

	public void forCDList(Model model) {
		List<CD> cdList = cdRepository.sqlCDAll();
		model.addAttribute("cdList", cdList);
	}

	public void forDVDList(Model model) {
		List<DVD> dvdList = dvdRepository.sqlDVDAll();
		model.addAttribute("dvdList", dvdList);
	}

	public void forKamishibaiList(Model model) {
		List<Kamishibai> kamishibaiList = kamishibaiRepository.sqlKamishibaiAll();
		model.addAttribute("kamishibaiList", kamishibaiList);
	}

	public void forRoomList(Model model) {
		List<Room> roomList = roomRepository.sqlRoomAll();
		model.addAttribute("roomList", roomList);
	}

	//検索ためのlibraryIdの保持
	//ヘッダーのための情報
	public void forLibraryId(Model model, Integer libraryId) {
		Optional<Library> optLibrary = libraryRepository.findById(libraryId);
		Library library = new Library();
		if (optLibrary.isPresent()) {
			library = optLibrary.get();
		} else {
			model.addAttribute("errorMsg", "不正な値:libraryId");
			library = libraryRepository.findById(1).get();
		}
		model.addAttribute("libraryId", libraryId);
		model.addAttribute("library", library);
	}

	public void forCategoryId(Model model, Integer categoryId) {
		Optional<Category> optCategory = categoryRepository.findById(categoryId);
		Category category = new Category();
		if (optCategory.isPresent()) {
			category = optCategory.get();
		} else {
			if (categoryId != 0) {
				model.addAttribute("errorMsg", "不正な値:categoryId");
			}
			category = categoryRepository.findById(1).get();
		}
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("category", category);
	}

}
