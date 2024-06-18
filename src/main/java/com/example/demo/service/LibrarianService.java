package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AnyJoinGenre;
import com.example.demo.entity.Book;
import com.example.demo.entity.CD;
import com.example.demo.entity.Category;
import com.example.demo.entity.DVD;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Kamishibai;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendItemForm;
import com.example.demo.entity.LendItemJoinStatus;
import com.example.demo.entity.LendItemJoinStatusJoinAny;
import com.example.demo.entity.Library;
import com.example.demo.entity.Room;
import com.example.demo.entity.Status;
import com.example.demo.repository.AnyJoinGenreRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CDRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DVDRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.KamishibaiRepository;
import com.example.demo.repository.LendItemJoinStatusJoinAnyRepository;
import com.example.demo.repository.LendItemJoinStatusRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LibraryRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.StatusRepository;

@Service
public class LibrarianService {

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

	//自作
	@Autowired
	AnyJoinGenreRepository anyJoinGenreRepository;

	@Autowired
	LendItemJoinStatusRepository lendItemJoinStatusRepository;

	@Autowired
	LendItemJoinStatusJoinAnyRepository lendItemJoinStatusJoinAnyRepository;

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
		List<Book> bookList = bookRepository.findAll();
		model.addAttribute("bookList", bookList);
	}

	public void forCDList(Model model) {
		List<CD> cdList = cdRepository.findAll();
		model.addAttribute("cdList", cdList);
	}

	public void forDVDList(Model model) {
		List<DVD> dvdList = dvdRepository.findAll();
		model.addAttribute("dvdList", dvdList);
	}

	public void forKamishibaiList(Model model) {
		List<Kamishibai> kamishibaiList = kamishibaiRepository.findAll();
		model.addAttribute("kamishibaiList", kamishibaiList);
	}

	public void forRoomList(Model model) {
		List<Room> roomList = roomRepository.findAll();
		model.addAttribute("roomList", roomList);
	}

	//検索ためのlibraryIdの保持
	//ヘッダーのための情報
	public void forLibraryId(Model model, Integer libraryId) {
		Optional<Library> OptLibrary = libraryRepository.findById(libraryId);
		Library library = new Library();
		if (OptLibrary.isPresent()) {
			library = OptLibrary.get();
		} else {
			model.addAttribute("errorMsg", "不正な値:libraryId");
			library = libraryRepository.findById(1).get();
		}
		model.addAttribute("libraryId", libraryId);
		model.addAttribute("library", library);
	}

	public void forCategoryId(Model model, Integer categoryId) {
		Optional<Category> OptCategory = categoryRepository.findById(categoryId);
		Category category = new Category();
		if (OptCategory.isPresent()) {
			category = OptCategory.get();
		} else {
			if (categoryId != 0) {
				model.addAttribute("errorMsg", "不正な値:categoryId");
			}
			category = categoryRepository.findById(1).get();
		}
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("category", category);
	}

	//貸出物新規登録用
	public void forLendItemForm(Model model, Integer categoryId, String address) {
		LendItemForm lendItemForm = new LendItemForm();
		model.addAttribute(address, lendItemForm);
		model.addAttribute("categoryId", categoryId);
	}

	public void forLendItemFormStore(Integer categoryId, Integer libraryId, LendItemForm lendItemForm) {
		LendItem lendItem = new LendItem();
		lendItem.setLibraryid(libraryId);
		lendItem.setCategoryId(categoryId);
		lendItem.setCreateDate(LocalDateTime.now());
		lendItem.setStatusId(lendItemForm.getStatusId());
		lendItem.setAnyId(lendItemForm.getAnyId());
		lendItem.setDeleted(false);
		lendItemRepository.save(lendItem);
	}

	//貸出物更新用
	public void forLendItemEdit(LendItem lendItem, Model model) {
		switch (lendItem.getCategoryId()) {
		case 1:
			List<Book> bookList = bookRepository.findAll();
			model.addAttribute("bookList", bookList);
			List<AnyJoinGenre> bookJoinGenre = anyJoinGenreRepository.sqlBookJoinGenreById(lendItem.getAnyId());
			model.addAttribute("lendAnyItemList", bookJoinGenre);
			break;
		case 2:
			List<CD> cdList = cdRepository.findAll();
			model.addAttribute("cdList", cdList);
			List<AnyJoinGenre> cdJoinGenre = anyJoinGenreRepository.sqlCDJoinGenreById(lendItem.getAnyId());
			model.addAttribute("lendAnyItemList", cdJoinGenre);
			break;
		case 3:
			List<DVD> dvdList = dvdRepository.findAll();
			model.addAttribute("dvdList", dvdList);
			List<AnyJoinGenre> dvdJoinGenre = anyJoinGenreRepository.sqlDVDJoinGenreById(lendItem.getAnyId());
			model.addAttribute("lendAnyItemList", dvdJoinGenre);
			break;
		case 4:
			List<Kamishibai> kamishibaiList = kamishibaiRepository.findAll();
			model.addAttribute("kamishibaiList", kamishibaiList);
			List<AnyJoinGenre> kamishibaiJoinGenre = anyJoinGenreRepository
					.sqlKamishibaiJoinGenreById(lendItem.getAnyId());
			model.addAttribute("lendAnyItemList", kamishibaiJoinGenre);
			break;
		case 5:
			Room room = roomRepository.findById(lendItem.getAnyId()).get();
			model.addAttribute("lendAnyItem", room);
			model.addAttribute("lendAnyItemList", room);
			break;
		}

		List<Genre> genreList = genreRepository
				.findByCategoryId(lendItem.getCategoryId());
		model.addAttribute("genreList", genreList);
		forStatusList(model);
		model.addAttribute("lendItem", lendItem);
	}

	//貸出処理ID検索用
	public void forLendProcessIdSearch(Integer lendItemId, Integer libraryId, Model model) {
		List<LendItemJoinStatus> tmpList = lendItemJoinStatusRepository.sqlLendProcessId(libraryId, lendItemId);
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

	//貸出処理キーワード検索用
	public void forLendProcessKeyword(Integer categoryId, Integer libraryId, String keyword, Model model) {
		keyword = "%" + keyword + "%";
		List<LendItemJoinStatusJoinAny> lendItemList = new ArrayList<LendItemJoinStatusJoinAny>();

		switch (categoryId) {
		case 1:
			lendItemList = lendItemJoinStatusJoinAnyRepository
					.sqlLendProcessBookKeyword(libraryId, keyword);
			break;
		case 2:
			lendItemList = lendItemJoinStatusJoinAnyRepository
					.sqlLendProcessCDKeyword(libraryId, keyword);
			break;
		case 3:
			lendItemList = lendItemJoinStatusJoinAnyRepository
					.sqlLendProcessDVDKeyword(libraryId, keyword);
			break;
		case 4:
			lendItemList = lendItemJoinStatusJoinAnyRepository
					.sqlLendProcessKamishibaiKeyword(libraryId, keyword);
			break;
		case 5:
			lendItemList = lendItemJoinStatusJoinAnyRepository
					.sqlLendProcessRoomKeyword(libraryId, keyword);
			break;
		}
		model.addAttribute("lendItemList", lendItemList);
	}
}
