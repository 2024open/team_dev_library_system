package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.AdminLendList;
import com.example.demo.entity.AdminLendRoom;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendingItem;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AdminLendListRepoitory;
import com.example.demo.repository.AdminLendRoomRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LendingItemRepository;
import com.example.demo.service.Common;
import com.example.demo.service.LibrarianService;

@Controller
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	LendingItemRepository lendingItemRepository;

	@Autowired
	AdminLendListRepoitory adminLendListRepository;

	@Autowired
	AdminLendRoomRepository adminLendRoomRepository;

	//貸出物一覧表示
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

	//貸出処理画面
	@GetMapping("/librarian/lendProcess")
	public String lendProcess(
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			@RequestParam(name = "lendItemId", defaultValue = "") String lendItemIdStr,
			Model model) {
		//libraryIdが不正
		if (libraryIdStr.isEmpty() || !Common.isParceInt(libraryIdStr)) {
			return "redirect:/librarian/lendItems";
		}
		//lendItemIdが不正
		if (libraryIdStr.isEmpty() || !Common.isParceInt(lendItemIdStr)) {
			Integer libraryId = Integer.parseInt(libraryIdStr);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcess";
		}

		Integer libraryId = Integer.parseInt(libraryIdStr);
		Integer lendItemId = Integer.parseInt(lendItemIdStr);

		librarianService.forLendProcessSearch(lendItemId, model);
		librarianService.forLibraryId(model, libraryId);
		return "librarianLendProcess";
	}

	//貸出処理
	@PostMapping("/librarian/lendProcess")
	public String lendProcessExecute(
			@RequestParam(name = "lendItemId", defaultValue = "") Integer lendItemId,
			@RequestParam(name = "libraryId", defaultValue = "1") Integer libraryId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "email", defaultValue = "") String email,
			Model model) {
		//email見つかるか
		List<Account> tmpList = accountRepository.findByEmail(email);
		Account lenderAccount = new Account();
		if (tmpList.size() == 1) {
			lenderAccount = tmpList.get(0);
		} else {
			return "librarianLendProcess";
		}
		LendItem updateItem = lendItemRepository.findById(lendItemId).get();

		if (updateItem.getStatusId() == 1) {
			//貸出処理
			updateItem.setStatusId(2);
			updateItem = lendItemRepository.save(updateItem);

			LendingItem lendingItem = new LendingItem();
			lendingItem.setLendItemId(updateItem.getLendItemId());
			lendingItem.setUserId(lenderAccount.getUserId());
			lendingItem.setStatusId(updateItem.getStatusId());
			//			lendingItem.setBorrowedDate();
			lendingItemRepository.save(lendingItem);

			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "lendProcessExecuted";
		} else if (updateItem.getStatusId() == 2) {
			//返却処理
			updateItem.setStatusId(1);
			updateItem = lendItemRepository.save(updateItem);

			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "lendProcessExecuted";
		}

		return "redirect:/librarian/lendProcess";
	}

	//貸出物更新画面
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

		librarianService.forLendItemEdit(lendItem, model);
		librarianService.forLibraryId(model, libraryId);
		return "lendItemEdit";
	}

	//TODO
	//貸出物更新処理
	@PostMapping("/librarian/lenditems/{id}/edit")
	public String update(
			@PathVariable("id") String lendItemIdStr,
			Model model) {
		return "redirect:/librarian/lenditems/{id}/edit";
	}

}
