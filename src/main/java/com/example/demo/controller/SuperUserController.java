package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.AllUserLendList;
import com.example.demo.model.SuperUser;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Status;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.StatusRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SuperUserController {

	@Autowired
	ALLUserLendListRepoitory allUserLendListrepository;

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired

	SuperUser superUser;


	GenreRepository genreRepository;
	
	@Autowired
	StatusRepository statusRepository;


	//貸出物一覧表示
	@GetMapping({ "/", "/home" })
	public String index(
			@RequestParam(value = "category", defaultValue = "") Integer category,
			Model model) {
		List<AllUserLendList> lendItemList = null;

		Map<Integer, String> genreMap = new HashMap<>();

		List<Genre> genreMapList = genreRepository.findAll();

		//Mapで格納
		for (Genre genre : genreMapList) {

			genreMap.put(genre.getGenreId(), genre.getGenreName());

		}
		
		Map<Integer, String> statusMap = new HashMap<>();

		List<Status> statusMapList = statusRepository.findAll();

		//Mapで格納
		for (Status status : statusMapList) {

			statusMap.put(status.getStatusId(), status.getStatusName());

		}

		model.addAttribute("genreMap", genreMap);
		model.addAttribute("statusMap", statusMap);

		if (category == null) {
			lendItemList = allUserLendListrepository.sqlALLUserLendJoin();
		} else if (category == 1) {
			lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
		} else if (category == 2) {
			lendItemList = allUserLendListrepository.sqlALLUserCDLendJoin();
		} else if (category == 3) {
			lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoin();
		} else if (category == 4) {
			lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoin();
		}
		model.addAttribute("lendItemList", lendItemList);

		return "index";
	}

	//	管理者ログイン画面表示
	@GetMapping({ "/su/login", "/su/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		//セッション情報を全てクリア
		session.invalidate();
		//エラーパラメータのチェック
		if (error.equals("notloggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}

		return "suLogin";
	}

	// 管理者ログイン処理
@PostMapping("/su/login")
public String login(
			@RequestParam("privilege") int privilege,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		List<Account> accountList = accountRepository.findByEmailAndPasswordAndPrivilege(email, password, privilege);

		

//		エラーチェック
		List<String> errorList = new ArrayList<>();
		if (email.length() == 0) {
			errorList.add("メールアドレスを入力してください");
		}

		if (password.length() == 0) {
			errorList.add("パスワードを入力してください");
		}

		Boolean nullParamater = (email.length() != 0) && (password.length() != 0);

		if (nullParamater && (accountList == null || accountList.size() == 0)) {
			errorList.add("メールアドレスとパスワードが一致しませんでした");
		}

		//		エラー時にログイン画面に戻る
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("email", email);
			return "suLogin";
		}

		
		// セッション管理されたアカウント情報に名前をセット
			superUser.setLibraryName(libraryName);

				// 「貸出物管理画面」へのリダイレクト
		return "redirect:/admin/lenditems";
	}
}
