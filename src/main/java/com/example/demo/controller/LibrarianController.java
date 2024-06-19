package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.LendItemForm;
import com.example.demo.entity.Notice;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.Common;
import com.example.demo.service.LendItemAddService;
import com.example.demo.service.LendItemEditService;
import com.example.demo.service.LendProcessService;
import com.example.demo.service.LibrarianLendItemService;
import com.example.demo.service.LibrarianService;
import com.example.demo.service.SuperUserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LibrarianController {
	//サービス
	@Autowired
	LibrarianService librarianService;

	@Autowired
	LibrarianLendItemService librarianLendItemService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	LendProcessService lendProcessService;

	@Autowired
	LendItemEditService lendItemEditService;

	@Autowired
	LendItemAddService lendItemAddService;

	@Autowired
	SuperUserService superUserService;

	//セッション系
	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	//リポジトリ
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	NoticeRepository noticeRepository;

	//ホーム
	//図書館変更のプルダウンがある
	@GetMapping({ "/librarian/home", "/librarian" })
	public String index(
			@RequestParam(name = "libraryId", defaultValue = "") String libraryIdStr,
			Model model) {
		if (!Common.isParceInt(libraryIdStr)) {
			Integer libraryId = superUser.getLibraryId();
			superUserService.setSuLibraryInfo(libraryId);

			librarianService.forLibraryList(model);
			librarianService.forLibraryId(model, libraryId);
			return "librarianHome";
		}
		Integer libraryId = Integer.parseInt(libraryIdStr);
		superUserService.setSuLibraryInfo(libraryId);

		librarianService.forLibraryList(model);
		librarianService.forLibraryId(model, libraryId);
		return "librarianHome";
	}

	//貸出物新規登録画面
	@GetMapping("/librarian/lenditems/add")
	public String lendItemCreate(
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			Model model) {
		if (!Common.isParceInt(categoryIdStr)) {
			return "redirect:/librarian/home";
		}
		Integer categoryId = Integer.parseInt(categoryIdStr);

		lendItemAddService.forLendItemForm(model, categoryId, "lendItemForm");

		librarianService.forLibraryList(model);
		librarianService.forCategoryList(model);
		return "lendItemAdd";
	}

	//貸出物新規登録処理
	@PostMapping("/librarian/lenditems/add")
	public String lendItemStore(
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			@ModelAttribute("lendItemForm") LendItemForm lendItemForm,
			Model model) {
		if (!Common.isParceInt(categoryIdStr)) {
			return "redirect:/librarian/home";
		}
		Integer categoryId = Integer.parseInt(categoryIdStr);

		lendItemAddService.forLendItemFormStore(categoryId, superUser.getLibraryId(), lendItemForm);

		librarianService.forLibraryList(model);
		librarianService.forCategoryList(model);
		librarianService.forCategoryId(model, categoryId);
		return "librarianLendItems";
	}

	//貸出物一覧表示
	// TODO ここもModelAttribute使えるネ
	@GetMapping("/librarian/lenditems")
	public String lendItem(
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			Model model) {
		if (!Common.isParceInt(categoryIdStr)) {
			return "redirect:/librarian/home";
		}
		Integer categoryId = Integer.parseInt(categoryIdStr);

		librarianLendItemService.forLendItemList(model, categoryId, superUser.getLibraryId());

		librarianService.forLibraryList(model);
		librarianService.forCategoryList(model);
		librarianService.forCategoryId(model, categoryId);
		return "librarianLendItems";
	}

	//貸出処理画面 検索処理
	// TODO ここもModelAttribute使えるネ
	@GetMapping("/librarian/lendProcess")
	public String lendProcess(
			@RequestParam(name = "lendItemId", defaultValue = "-1") String lendItemIdStr,
			@RequestParam(name = "categoryId", defaultValue = "0") String categoryIdStr,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			Model model) {
		//lendItemIdが不正
		if (!Common.isParceInt(lendItemIdStr) && !lendItemIdStr.isEmpty()) {
			String errorMsg = "不正な値:lendItemId";
			model.addAttribute("errorMsg", errorMsg);
			librarianService.forCategoryList(model);
			return "librarianLendProcess";
		}
		//categoryIdが不正
		if (!Common.isParceInt(categoryIdStr) && !categoryIdStr.isEmpty()) {
			String errorMsg = "不正な値:categoryId";
			model.addAttribute("errorMsg", errorMsg);
			librarianService.forCategoryList(model);
			return "librarianLendProcess";
		}

		//本体
		if (!lendItemIdStr.isEmpty() && Integer.parseInt(lendItemIdStr) >= 0) {
			//ID検索
			Integer lendItemId = Integer.parseInt(lendItemIdStr);
			lendProcessService.forLendProcessIdSearch(lendItemId, superUser.getLibraryId(), model);
			librarianService.forCategoryId(model, 1);
		} else if (!categoryIdStr.isEmpty()) {
			//キーワード検索
			Integer categoryId = Integer.parseInt(categoryIdStr);
			lendProcessService.forLendProcessKeyword(categoryId, superUser.getLibraryId(), keyword, model);
			librarianService.forCategoryId(model, categoryId);
			model.addAttribute("keyword", keyword);
		}

		librarianService.forCategoryList(model);
		return "librarianLendProcess";
	}

	//貸出処理
	// TODO ここもModelAttribute使えるネ
	@PostMapping("/librarian/lendProcess")
	public String lendProcessExecute(
			@RequestParam(name = "lendItemId", defaultValue = "") Integer lendItemId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "email", defaultValue = "") String email,
			Model model) {

		String returnURL = lendProcessService.forLendProcess(model, superUser.getLibraryId(), lendItemId, title, email);
		return returnURL;
	}

	//貸出物更新画面
	@GetMapping("/librarian/lenditems/{id}/edit")
	public String lendItemEdit(
			@PathVariable("id") String lendItemIdStr,
			Model model) {
		if (!Common.isParceInt(lendItemIdStr)) {
			return "redirect:/librarian/home";
		}
		Integer lendItemId = Integer.parseInt(lendItemIdStr);

		//TODO
		lendItemEditService.forLendItemEdit(lendItemId, model);

		return "lendItemEdit";
	}

	//貸出物更新処理
	//post処理だしプルダウンだしstatusIdはInteger以外こなさそう
	@PostMapping("/librarian/lenditems/{id}/edit")
	public String lendItemUpdate(
			@PathVariable("id") Integer lendItemId,
			@RequestParam(name = "statusId", defaultValue = "") Integer statusId,
			@RequestParam(name = "anyId", defaultValue = "") Integer anyId,
			Model model) {

		lendItemEditService.forEditExecute(lendItemId, statusId, anyId);

		lendItemEditService.forLendItemEdit(lendItemId, model);
		return "lendItemEdit";
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
	// TODO ここもModelAttribute使えるネ
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
	// TODO ここもModelAttribute使えるネ
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
	// TODO ここもModelAttribute使えるネ
	public String noticeDelete(@PathVariable("noticeId") Integer noticeId, Model model) {

		noticeRepository.deleteById(noticeId);

		return "redirect:/librarian/notice";
	}

}
