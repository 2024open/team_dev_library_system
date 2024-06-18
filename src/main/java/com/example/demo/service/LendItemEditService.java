package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

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
