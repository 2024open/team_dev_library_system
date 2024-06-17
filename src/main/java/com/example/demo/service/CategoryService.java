package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AnyJoinGenre;
import com.example.demo.entity.Book;
import com.example.demo.entity.CD;
import com.example.demo.entity.CategoryDataForm;
import com.example.demo.entity.DVD;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Kamishibai;
import com.example.demo.entity.Room;
import com.example.demo.repository.AnyJoinGenreRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CDRepository;
import com.example.demo.repository.DVDRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.KamishibaiRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class CategoryService {

	//サービス
	@Autowired
	LibrarianService librarianService;

	//基本
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
	GenreRepository genreRepository;

	//カテゴリ編集画面用
	public void forCategoryGenreList(Model model, Integer categoryId, String address) {
		List<Genre> genreList = genreRepository.sqlGenreListId(categoryId);
		model.addAttribute(address, genreList);
	}

	//カテゴリーデータinsert処理
	public void forCategoryDataStore(Model model, Integer categoryId, CategoryDataForm categoryDataForm) {
		switch (categoryId) {
		case 1:
			Book book = new Book();
			book.setTitle(categoryDataForm.getTitle());
			book.setAuthor(categoryDataForm.getAuthor());
			book.setPublisher(categoryDataForm.getPublisher());
			book.setGenreId(categoryDataForm.getGenreId());
			book.setDeleted(false);
			book = bookRepository.save(book);
			break;
		case 2:
			CD cd = new CD();
			cd.setTitle(categoryDataForm.getTitle());
			cd.setAuthor(categoryDataForm.getAuthor());
			cd.setPublisher(categoryDataForm.getPublisher());
			cd.setGenreId(categoryDataForm.getGenreId());
			cd.setDeleted(false);
			cd = cdRepository.save(cd);
			break;
		case 3:
			DVD dvd = new DVD();
			dvd.setTitle(categoryDataForm.getTitle());
			dvd.setAuthor(categoryDataForm.getAuthor());
			dvd.setPublisher(categoryDataForm.getPublisher());
			dvd.setGenreId(categoryDataForm.getGenreId());
			dvd.setDeleted(false);
			dvd = dvdRepository.save(dvd);
			break;
		case 4:
			Kamishibai kamishibai = new Kamishibai();
			kamishibai.setTitle(categoryDataForm.getTitle());
			kamishibai.setAuthor(categoryDataForm.getAuthor());
			kamishibai.setPublisher(categoryDataForm.getPublisher());
			kamishibai.setGenreId(categoryDataForm.getGenreId());
			kamishibai.setDeleted(false);
			kamishibai = kamishibaiRepository.save(kamishibai);
			break;
		case 5:
			Room room = new Room();
			room.setRoomName(categoryDataForm.getTitle());
			room.setDeleted(false);
			room = roomRepository.save(room);
			break;

		}
	}

	//B, C, D, K, Rの一覧表示用
	public void forCategoryDataList(Model model, Integer categoryId) {
		switch (categoryId) {
		case 1:
			List<AnyJoinGenre> bookJoinGenre = anyJoinGenreRepository.sqlBookJoinGenreAll();
			model.addAttribute("lendAnyItemList", bookJoinGenre);
			break;
		case 2:
			List<AnyJoinGenre> cdJoinGenre = anyJoinGenreRepository.sqlCDJoinGenreAll();
			model.addAttribute("lendAnyItemList", cdJoinGenre);
			break;
		case 3:
			librarianService.forDVDList(model);
			List<AnyJoinGenre> dvdJoinGenre = anyJoinGenreRepository.sqlDVDJoinGenreAll();
			model.addAttribute("lendAnyItemList", dvdJoinGenre);
			break;
		case 4:
			List<AnyJoinGenre> kamishibaiJoinGenre = anyJoinGenreRepository
					.sqlKamishibaiJoinGenreAll();
			model.addAttribute("lendAnyItemList", kamishibaiJoinGenre);
			break;
		case 5:
			List<Room> room = roomRepository.sqlRoomAll();
			model.addAttribute("lendAnyItemList", room);
			break;
		}
	}

	//B, C, D, K, Rの詳細表示用
	public void forCategoryDataDetail(Model model, Integer categoryId, Integer anyId, String address) {
		switch (categoryId) {
		case 1:
			Book book = bookRepository.findById(anyId).get();
			model.addAttribute(address, book);
			break;
		case 2:
			CD cd = cdRepository.findById(anyId).get();
			model.addAttribute(address, cd);
			break;
		case 3:
			DVD dvd = dvdRepository.findById(anyId).get();
			model.addAttribute(address, dvd);
			break;
		case 4:
			Kamishibai kamishibai = kamishibaiRepository.findById(anyId).get();
			model.addAttribute(address, kamishibai);
			break;
		case 5:
			Room room = roomRepository.findById(anyId).get();
			model.addAttribute(address, room);
			break;
		}
	}

	//更新処理
	public void forCategoryDataEdit(
			Integer categoryId,
			Book book,
			CD cd,
			DVD dvd,
			Kamishibai kamishibai,
			Room room) {
		switch (categoryId) {
		case 1:
			bookRepository.save(book);
			break;
		case 2:
			cdRepository.save(cd);
			break;
		case 3:
			dvdRepository.save(dvd);
			break;
		case 4:
			kamishibaiRepository.save(kamishibai);
			break;
		case 5:
			roomRepository.save(room);
			break;
		}
	}

}
