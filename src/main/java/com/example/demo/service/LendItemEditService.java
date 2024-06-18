package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AnyJoinGenre;
import com.example.demo.entity.Book;
import com.example.demo.entity.CD;
import com.example.demo.entity.DVD;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Kamishibai;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.Room;
import com.example.demo.repository.AnyJoinGenreRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CDRepository;
import com.example.demo.repository.DVDRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.KamishibaiRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class LendItemEditService {
	//サービス
	@Autowired
	LibrarianService librarianService;

	//リポジトリ
	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	GenreRepository genreRepository;

	//カテゴリーデータ系
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

	//貸出物更新用
	public void forLendItemEdit(Integer lendItemId, Model model) {
		Optional<LendItem> optLendItem = lendItemRepository.findById(lendItemId);
		LendItem lendItem = new LendItem();

		if (optLendItem.isPresent()) {
			lendItem = optLendItem.get();
		}
		switch (lendItem.getCategoryId()) {
		case 1:
			List<Book> bookList = bookRepository.sqlBookAll();
			model.addAttribute("bookList", bookList);
			AnyJoinGenre bookJoinGenre = anyJoinGenreRepository
					.sqlBookJoinGenreById(lendItem.getAnyId()).get(0);
			model.addAttribute("lendAnyItem", bookJoinGenre);
			break;
		case 2:
			List<CD> cdList = cdRepository.sqlCDAll();
			model.addAttribute("cdList", cdList);
			AnyJoinGenre cdJoinGenre = anyJoinGenreRepository
					.sqlCDJoinGenreById(lendItem.getAnyId()).get(0);
			model.addAttribute("lendAnyItem", cdJoinGenre);
			break;
		case 3:
			List<DVD> dvdList = dvdRepository.sqlDVDAll();
			model.addAttribute("dvdList", dvdList);
			AnyJoinGenre dvdJoinGenre = anyJoinGenreRepository
					.sqlDVDJoinGenreById(lendItem.getAnyId()).get(0);
			model.addAttribute("lendAnyItem", dvdJoinGenre);
			break;
		case 4:
			List<Kamishibai> kamishibaiList = kamishibaiRepository.sqlKamishibaiAll();
			model.addAttribute("kamishibaiList", kamishibaiList);
			AnyJoinGenre kamishibaiJoinGenre = anyJoinGenreRepository
					.sqlKamishibaiJoinGenreById(lendItem.getAnyId()).get(0);
			model.addAttribute("lendAnyItem", kamishibaiJoinGenre);
			break;
		case 5:
			List<Room> roomList = roomRepository.sqlRoomAll();
			model.addAttribute("roomList", roomList);
			Room room = roomRepository.findById(lendItem.getAnyId()).get();
			model.addAttribute("lendAnyItem", room);
			break;
		}

		List<Genre> genreList = genreRepository
				.findByCategoryId(lendItem.getCategoryId());
		model.addAttribute("genreList", genreList);
		librarianService.forStatusList(model);
		model.addAttribute("lendItem", lendItem);
	}

	//貸出物更新処理
	public void forEditExecute(Integer lendItemId, Integer statusId, Integer anyId) {
		LendItem lendItem = lendItemRepository.findById(lendItemId).get();
		lendItem.setStatusId(statusId);
		lendItem.setAnyId(anyId);
		lendItem.setUpdateDate(LocalDateTime.now());
		lendItemRepository.save(lendItem);
	}

}
