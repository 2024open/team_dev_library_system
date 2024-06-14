package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.example.demo.entity.Notice;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AdminLendListRepoitory;
import com.example.demo.repository.AdminLendRoomRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LendingItemRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.service.Common;
import com.example.demo.service.LibrarianService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LibrarianController {
	//Service
	@Autowired
	LibrarianService librarianService;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AdminLendRoomRepository adminLendRoomRepository;

	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	LendingItemRepository lendingItemRepository;

	@Autowired
	AdminLendListRepoitory adminLendListRepository;

	//貸出物一覧表示
	@GetMapping({ "/librarian/lenditems", "/librarian" })
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

		librarianService.forLibraryList(model);
		librarianService.forCategoryList(model);
		librarianService.forLibraryId(model, libraryId);

		return "librarianLendItems";
	}

	//貸出処理画面 検索処理
	@GetMapping("/librarian/lendProcess")
	public String lendProcess(
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			@RequestParam(name = "lendItemId", defaultValue = "-1") String lendItemIdStr,
			@RequestParam(name = "categoryId", defaultValue = "-1") String categoryIdStr,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {
		//libraryIdが不正
		if (libraryIdStr.isEmpty() || !Common.isParceInt(libraryIdStr)) {
			return "redirect:/librarian/lendItems";
		}
		//lendItemIdが不正
		if (!Common.isParceInt(lendItemIdStr) && !lendItemIdStr.isEmpty()) {
			Integer libraryId = Integer.parseInt(libraryIdStr);
			String errorMsg = "不正な値:lendItemId";
			model.addAttribute("errorMsg", errorMsg);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcess";
		}
		//categoryIdが不正
		if (!Common.isParceInt(categoryIdStr) && !categoryIdStr.isEmpty()) {
			Integer libraryId = Integer.parseInt(libraryIdStr);
			String errorMsg = "不正な値:categoryId";
			model.addAttribute("errorMsg", errorMsg);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcess";
		}

		Integer libraryId = Integer.parseInt(libraryIdStr);

		if (!lendItemIdStr.isEmpty() && Integer.parseInt(lendItemIdStr) >= 0) {
			//ID検索
			Integer lendItemId = Integer.parseInt(lendItemIdStr);
			librarianService.forLendProcessIdSearch(lendItemId, libraryId, model);
		} else if (!categoryIdStr.isEmpty()) {
			//キーワード検索
			Integer categoryId = Integer.parseInt(categoryIdStr);
			librarianService.forLendProcessKeyword(categoryId, libraryId, keyword, model);
		}
		librarianService.forCategoryList(model);
		librarianService.forLibraryId(model, libraryId);
		return "librarianLendProcess";
	}

	//貸出処理
	@PostMapping("/librarian/lendProcess")
	public String lendProcessExecute(
			@RequestParam(name = "libraryId", defaultValue = "1") Integer libraryId,
			@RequestParam(name = "lendItemId", defaultValue = "") Integer lendItemId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "email", defaultValue = "") String email,
			Model model) {
		//email見つかるか
		List<Account> tmpList = accountRepository.findByEmail(email);
		Account lenderAccount = new Account();
		if (tmpList.size() == 1) {
			lenderAccount = tmpList.get(0);
		} else {
			String errorMsg = "メールアドレスが間違っています";
			model.addAttribute("errorMsg", errorMsg);
			librarianService.forLendProcessIdSearch(lendItemId, libraryId, model);
			librarianService.forCategoryList(model);
			librarianService.forLibraryId(model, libraryId);
			return "librarianLendProcess";
		}

		LendItem updateItem = lendItemRepository.findById(lendItemId).get();
		if (updateItem.getStatusId() == 1) {
			//貸出物テーブルのステータス変更
			updateItem.setStatusId(2);
			updateItem = lendItemRepository.save(updateItem);

			//貸出中テーブルに追加
			LendingItem lendingItem = new LendingItem();
			lendingItem.setLendItemId(updateItem.getLendItemId());
			lendingItem.setUserId(lenderAccount.getUserId());
			lendingItem.setStatusId(updateItem.getStatusId());
			lendingItem.setBorrowedDate(LocalDate.now());
			lendingItem.setReturnDate(null); //返却日はnull
			lendingItemRepository.save(lendingItem);

			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "lendProcessExecuted";
		} else if (updateItem.getStatusId() == 2) {
			//貸出物テーブルのステータス変更
			updateItem.setStatusId(1);
			updateItem = lendItemRepository.save(updateItem);

			//貸出中テーブルの更新
			List<LendingItem> returnItemList = lendingItemRepository.findByLendItemId(lendItemId);
			LendingItem returnItem = returnItemList.get(0);
			returnItem.setReturnDate(LocalDate.now());
			returnItem.setStatusId(1);
			lendingItemRepository.save(returnItem);

			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "lendProcessExecuted";
		} else {
			String errorMsg = "貸出不可";

			model.addAttribute("errorMsg", errorMsg);
			model.addAttribute("lendItem", updateItem);
			model.addAttribute("title", title);
			librarianService.forLibraryId(model, libraryId);
			return "lendProcessExecuted";
		}
	}

	//貸出物更新画面
	@GetMapping("/librarian/lenditems/{id}/edit")
	public String lendItemEdit(
			@PathVariable("id") String lendItemIdStr,
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			Model model) {

		if (!Common.isParceInt(lendItemIdStr) ||
				!Common.isParceInt(libraryIdStr)) {
			return "redirect:/librarian/lenditems";
		}
		Integer lendItemId = Integer.parseInt(lendItemIdStr);
		Integer libraryId = Integer.parseInt(libraryIdStr);

		LendItem lendItem = lendItemRepository.findById(lendItemId).get();

		librarianService.forLendItemEdit(lendItem, model);
		librarianService.forLibraryId(model, libraryId);
		return "lendItemEdit";
	}

	//貸出物更新処理
	//post処理だしプルダウンだしstatusIdはInteger以外こなさそう
	@PostMapping("/librarian/lenditems/{id}/edit")
	public String lendItemUpdate(
			@PathVariable("id") Integer lendItemId,
			@RequestParam("statusId") Integer statusId,
			@RequestParam("anyId") Integer anyId,
			Model model) {
		LendItem lendItem = lendItemRepository.findById(lendItemId).get();
		lendItem.setStatusId(statusId);
		lendItem.setAnyId(anyId);
		lendItem.setUpdateDate(LocalDateTime.now());
		lendItemRepository.save(lendItem);
		return "redirect:/librarian/lenditems/{id}/edit";
	}

	//---------------------------------------------------//
	//--------------お知らせの処理-----------------------//
	//---------------------------------------------------//
	// お知らせ一覧表示
	@GetMapping("/librarian/notice")
	public String notice(Model model) {
		List<Notice> noticeList = noticeRepository.findAll();
		model.addAttribute("notices", noticeList);
		return "noticeAdmin";
	}

	// お知らせ新規登録画面の表示
	@GetMapping("/librarian/notice/add")
	public String noticeCreate() {
		return "addNotice";
	}

	// 新規登録処理
	@PostMapping("/librarian/notice/add")
	public String noticeStore(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "content", defaultValue = "") String content,
			Model model) {

		//    	エラー処理
		List<String> errorList = new ArrayList<>();

		//		文字数確認
		if (title.isBlank()) {
			errorList.add("タイトルは必須です");
		} else if (title.length() > 30) {
			errorList.add("タイトルは30字以内で入力してください");
		}
		if (content.isBlank()) {
			errorList.add("内容は必須です");
		} else if (title.length() > 30) {
			errorList.add("内容は100字以内で入力してください");
		}

		// エラー発生時は新規登録画面に戻す

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			return "addNotice";
		}

		Integer userId = superUser.getUserId();
		Integer libraryId = superUser.getLibraryId();
		// Noticeオブジェクトの生成
		Notice notice = new Notice(libraryId, userId, title, content);

		// noticeテーブルへの反映（INSERT）
		noticeRepository.save(notice);
		return "redirect:/librarian/notice";
	}

	// お知らせ更新画面表示
	@GetMapping("/librarian/notice/{noticeId}/edit")
	public String noticeEdit(
			@PathVariable("noticeId") Integer noticeId,
			Model model) {

		// noticeテーブルをID（主キー）で検索
		Notice notice = noticeRepository.findById(noticeId).get();
		model.addAttribute("notice", notice);
		return "editNotice";
	}

	// お知らせ更新処理
	@PostMapping("/librarian/notice/{noticeId}/edit")
	public String noticeUpdate(
			@PathVariable("noticeId") Integer noticeId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "content", defaultValue = "") String content,
			Model model) {
//    	エラー処理
		List<String> errorList = new ArrayList<>();

		//		文字数確認
		if (title.isBlank()) {
			errorList.add("タイトルは必須です");
		} else if (title.length() > 30) {
			errorList.add("タイトルは30字以内で入力してください");
		}
		if (content.isBlank()) {
			errorList.add("内容は必須です");
		} else if (title.length() > 30) {
			errorList.add("内容は100字以内で入力してください");
		}

		// エラー発生時は更新画面に戻す

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("title", title);
			model.addAttribute("content", content);
			
			// noticeテーブルをID（主キー）で検索
			Notice notice = noticeRepository.findById(noticeId).get();
			model.addAttribute("notice", notice);
			
			return "editNotice";
		}
		
		Integer libraryId = superUser.getLibraryId();
		Integer userId = superUser.getUserId();

		Notice notice = new Notice(noticeId, libraryId, userId, title, content);

		noticeRepository.save(notice);

		return "redirect:/librarian/notice";
	}

	// お知らせ削除処理
	@PostMapping("/librarian/notice/{noticeId}/delete")
	public String noticeDelete(@PathVariable("noticeId") Integer noticeId, Model model) {

		noticeRepository.deleteById(noticeId);

		return "redirect:/librarian/notice";
	}

}
