package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AdminLendList;
import com.example.demo.entity.AdminLendRoom;
import com.example.demo.entity.LendItem;
import com.example.demo.repository.AdminLendListRepoitory;
import com.example.demo.repository.AdminLendRoomRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.service.Common;
import com.example.demo.service.LibrarianService;

@Controller
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AdminLendListRepoitory adminLendListRepository;

	@Autowired
	AdminLendRoomRepository adminLendRoomRepository;

	@GetMapping("/librarian/lenditems")
	public String lendItem(
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			@RequestParam(name = "genreId", defaultValue = "") String genreIdStr,
			Model model) {

		//		if (!(Common.isParceInt(libraryIdStr) && Common.isParceInt(categoryIdStr) && Common.isParceInt(genreIdStr))) {
		//			return "redirect:/su/home";
		//		}

		List<AdminLendList> LendJoinAny = new ArrayList<AdminLendList>();
		LendJoinAny = null;
		Integer libraryId = Integer.parseInt(libraryIdStr);
		Integer categoryId = Integer.parseInt(categoryIdStr);
		//		Integer genreId = Integer.parseInt(genreIdStr);

		switch (categoryId) {
		case 1:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinBook(libraryId);
			break;
		case 2:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinCD(libraryId);
			break;
		case 3:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinDVD(libraryId);
			break;
		case 4:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinKamishibai(libraryId);
			break;
		case 5:
			List<AdminLendRoom> LendJoinRoom = new ArrayList<AdminLendRoom>();
			LendJoinRoom = adminLendRoomRepository.sqlAdminLendJoinRoom(libraryId);
			model.addAttribute("LendJoinRoom", LendJoinRoom);
			break;
		}
		model.addAttribute("LendJoinAny", LendJoinAny);

		librarianService.forLibraryPullDown(model);
		librarianService.forCategoryPullDown(model);
		librarianService.forLibraryId(model, libraryId);

		return "librarianLendItems";
	}

	@GetMapping("/librarian/lenditems/{id}/edit")
	public String edit(
			@PathVariable("id") String lendItemIdStr,
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			Model model) {

		if (!(Common.isParceInt(lendItemIdStr) &&
				Common.isParceInt(libraryIdStr))) {
			return "redirect:/librarian/lenditems";
		}
		Integer lendItemId = Integer.parseInt(lendItemIdStr);
		Integer libraryId = Integer.parseInt(libraryIdStr);

		LendItem lendItem = lendItemRepository.findById(lendItemId).get();

		librarianService.forLendItemDetail(lendItem, model);
		librarianService.forLibraryId(model, libraryId);
		return "lendItemDetail";
	}

}
