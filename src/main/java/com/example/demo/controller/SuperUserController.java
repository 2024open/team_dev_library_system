package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.entity.Library;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.LibraryRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class SuperUserController {
	//セッション系
	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	//リポジトリ
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	LibraryRepository libraryRepository;

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

	//ログイン実行
	@PostMapping("/su/login")
	public String login(
			@RequestParam("libraryName") int libraryId,
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

		Boolean nullParameter = (email.length() != 0) && (password.length() != 0);

		if (nullParameter && (accountList == null || accountList.size() == 0)) {
			errorList.add("メールアドレスとパスワードが一致しませんでした");
		}

		//		エラー時にログイン画面に戻る
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("email", email);
			return "suLogin";
		}

		//セッション管理されたSuperUserモデルに図書館ID、図書館名、ユーザーID、権限をセット
		List<Library> libraries = libraryRepository.findByLibraryId(libraryId);

		Library library = libraries.get(0);
		superUser.setLibraryId(libraryId);
		superUser.setLibraryName(library.getLibraryName());

		List<Account> accounts = accountRepository.findByEmail(email);
		Account account = accounts.get(0);
		superUser.setUserId(account.getUserId());
		superUser.setPrivilege(account.getPrivilege());

		if (privilege == 0) {
			// 「司書のホーム画面」へのリダイレクト
			return "redirect:/admin/home";
		} else {
			// 「司書のホーム画面」へのリダイレクト
			return "redirect:/librarian/home";
		}

	}

}
