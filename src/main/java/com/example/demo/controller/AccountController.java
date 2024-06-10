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
import com.example.demo.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	AccountRepository accountRepository;

	//ログイン実行
	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		//エラー処理
		List<String> errorList = new ArrayList<>();
		//文字数のチェック
		if (email.length() >= 101) {
			errorList.add("メールアドレスは100字以内で入力してください");
		}
		if (email.length() == 0) {
			errorList.add("メールアドレスを入力してください");
		}
		if (password.length() >= 31) {
			errorList.add("パスワードは30字以内で入力してaaaaください");
		}
		if (password.length() == 0) {
			errorList.add("パスワードを入力してください");
		}
		if (!errorList.isEmpty()) {
			model.addAttribute("errorLists", errorList);
			return "login";
		}

		List<Account> accountList = accountRepository.findByEmailAndPassword(email, password);
		if (accountList == null || accountList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("errorLists", "メールアドレスとパスワードが一致しませんでした");
			return "login";
		}
		Account account = accountList.get(0);
		//セッション管理されたアカウント情報に名前をセット
		session.setAttribute("userName", account.getUserName());
		session.setAttribute("userId", account.getUserId());

		//[/items]へリダイレクト
		return "redirect:/lendItems";
	}

	//　会員登録画面表示
	@GetMapping("/users/new")
	public String customer() {
		return "newAccount";
	}

	//	会員登録処理
	@PostMapping("/users/new")
	public String store(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "nickname", defaultValue = "") String nickname,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String password,
			@RequestParam(value = "password_confirm", defaultValue = "") String password_confirm,

			Model model) {

		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (userName.length() == 0) {
			errorList.add("名前は必須です");
		} else if (userName.length() > 30) {
			errorList.add("名前は50字以下で入力してください");
		}

		if (nickname.length() == 0) {
			errorList.add("ニックネームは必須です");
		} else if (nickname.length() > 60) {
			errorList.add("ニックネームは50字以下で入力してください");
		}

		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		} else if (email.length() > 50) {
			errorList.add("メールアドレスは100字以下で入力してください");

		} else if (accountRepository.findByEmail(email).size() != 0) {
			errorList.add("登録済みのメールアドレスです");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		} else if (password.length() < 6) {
			errorList.add("パスワードは6文字以上で入力してください");
		} else if (password.length() > 20) {
			errorList.add("パスワードは30字以下で入力してください");
		}

		if (!password.equals(password_confirm)) {
			errorList.add("パスワードが一致しません");
		}

		// エラー発生時は会員登録フォームに戻す

		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("userName", userName);
			model.addAttribute("nickname", nickname);
			model.addAttribute("email", email);
			model.addAttribute("password", password);
			return "newAccount";
		}

		// Accountオブジェクトの生成
		Account account = new Account(userName, nickname, email, password);

		// accountテーブルへの反映（INSERT）
		accountRepository.save(account);

		return "redirect:/login";
	}

	//ログイン画面表示
	@GetMapping({ "", "/login", "/logout" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		//セッション情報を全てクリア
		session.invalidate();
		//エラーパラメータのチェック
		if (error.equals("notloggedIn")) {
			model.addAttribute("message", "ログインしてください");
		}
		return "login";
	}

}
