package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.commonEnum.GenreMessage;
import com.example.demo.entity.Account;
import com.example.demo.entity.Category;
import com.example.demo.entity.Genre;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.Common;
import com.example.demo.service.LendItemAddService;
import com.example.demo.service.LendItemEditService;
import com.example.demo.service.LendProcessService;
import com.example.demo.service.LibrarianLendItemService;
import com.example.demo.service.LibrarianService;

@Controller
public class AdminController {
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

	//リポジトリ
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/admin/lenditems")
	public String lendItem(
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			Model model) {
		if (!Common.isParceInt(libraryIdStr)) {
			return "redirect:/librarian/home";
		}
		if (!Common.isParceInt(categoryIdStr)) {
			return "redirect:/librarian/home";
		}
		Integer libraryId = Integer.parseInt(libraryIdStr);
		Integer categoryId = Integer.parseInt(categoryIdStr);

		librarianLendItemService.forLendItemList(model, categoryId, libraryId);

		librarianService.forLibraryList(model);
		librarianService.forCategoryList(model);
		librarianService.forLibraryId(model, libraryId);
		librarianService.forCategoryId(model, categoryId);
		return "librarianLendItems";
	}

	@GetMapping("/admin/home")
	public String home() {
		// 処理は後で書く
		return "adminHome";
	}

	// 管理者利用者情報管理画面
	@GetMapping("/admin/accountManager")
	public String index(
			@RequestParam(value = "userName", defaultValue = "%") String userName,
			Model model) {
		List<Account> accountList;
		if (userName != null) {
			accountList = accountRepository.findByUserNameLike("%" + userName + "%");
		} else {
			accountList = accountRepository.findAll();
		}
		model.addAttribute("accountList", accountList);
		return "accountManager";
	}

	//BANの処理
	@PostMapping("/admin/accountManager")
	public String ban(
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "ban", defaultValue = "") boolean ban,
			Model model) {
		//AccountのuserIdを取得
		Account account = accountRepository.findById(userId).get();
		if (ban == true) {
			ban = false;
		} else {
			ban = true;
		}
		//Accountにbanの結果をセット
		account.setBan(ban);
		accountRepository.save(account);
		return "redirect:/admin/accountManager";
	}

	// ジャンルの表示
	@GetMapping("/admin/genre")
	public String genre(
			@RequestParam(value = "messageId", defaultValue = "") String messageId,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "btnView", defaultValue = "") String btnView,
			@RequestParam(value = "deleted", defaultValue = "") Boolean deleted,
			Model model) {

		// 	enumによるエラー表示
		GenreMessage genreMessage = GenreMessage.getById(messageId);
		if (genreMessage != null) {
			model.addAttribute("message", genreMessage.getMessage());
		}

		// 	ジャンル一覧表示切り替え用
		List<Genre> genreList = null;

		if (deleted == null) {
			model.addAttribute("btnView", "削除");
			if (categoryId == null) {
				// 	ジャンル全表示
				genreList = genreRepository.findByDeletedFalseOrderByCategoryIdAsc();
			} else {
				//	カテゴリーで絞り込み表示			
				genreList = genreRepository.findByDeletedFalseAndCategoryId(categoryId);
			}
		} else {
			//　削除したジャンル全表示 
			genreList = genreRepository.findByDeletedTrueOrderByCategoryIdAsc();
			model.addAttribute("btnView", "復元");

		}

		// 	カテゴリーから会議室を除く
		List<Category> categoryList = categoryRepository.findAll();
		categoryList.remove(4);
		model.addAttribute("categoryList", categoryList);

		// Mapに格納
		Map<Integer, String> categoryMap = new HashMap<>();
		for (Category category : categoryList) {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		}

		model.addAttribute("genreList", genreList);
		model.addAttribute("categoryMap", categoryMap);

		return "genre";
	}

	//ジャンルの追加
	@PostMapping("/admin/genre/add")
	public String genreAdd(
			@RequestParam(value = "categoryId") String strCategoryId,
			@RequestParam(value = "genreName", defaultValue = "") String genreName,
			Model model,
			RedirectAttributes redirectAttributes) {

		int categoryId = Integer.parseInt(strCategoryId);

		//    	エラー処理
		List<Genre> genreList = genreRepository.findByCategoryIdAndGenreName(categoryId, genreName);

		if (genreName.isBlank()) {
			return "redirect:/admin/genre?messageId=1";
		}

		if (!genreList.isEmpty()) {
			return "redirect:/admin/genre?messageId=2";
		}

		//	ジャンルの初期設定
		Boolean deleted = false;

		// Genreオブジェクトの生成
		Genre newGenre = new Genre();
		newGenre.setGenreName(genreName);
		newGenre.setCategoryId(categoryId);
		newGenre.setDeleted(deleted);

		// genreテーブルへの反映（INSERT）
		genreRepository.save(newGenre);
		return "redirect:/admin/genre?messageId=3";
	}

	//	  ジャンル削除処理
	@PostMapping("/admin/genre/{genreId}/delete")
	public String noticeDelete(@PathVariable("genreId") Integer genreId,
			Model model) {

		Genre updateGenre = genreRepository.findById(genreId).get();

		if (updateGenre.getDeleted() == false) {

			//			//削除フラグ管理
			updateGenre.setDeleted(true);

			updateGenre = genreRepository.save(updateGenre);
		} else {
			updateGenre.setDeleted(false);
			updateGenre = genreRepository.save(updateGenre);
		}

		return "redirect:/admin/genre";

	}
}
