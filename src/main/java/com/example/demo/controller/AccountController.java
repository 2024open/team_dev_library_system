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
    AccountRepository accountrepository;

    //ログイン画面表示
    @GetMapping({"","/login","/logout"})
    public String index(
            @RequestParam(name = "error",defaultValue="")String error,
            Model model){
        //セッション情報を全てクリア
        session.invalidate();
        //エラーパラメータのチェック
        if(error.equals("notloggedIn")){
            model.addAttribute("message","ログインしてください");
        }
        return "login";
    }

    //ログイン実行
    @PostMapping("/login")
    public String login(
            @RequestParam("email")String email,
            @RequestParam("password")String password,
            Model model){
        //エラー処理
        List<String> errorList = new ArrayList<>();
        //文字数のチェック
        if(email.length() >= 101) {
            errorList.add("メールアドレスは100字以内で入力してください");
        }
        if(email.length()==0){
            errorList.add("メールアドレスを入力してください");
        }
        if(password.length() >= 31) {
            errorList.add("パスワードは30字以内で入力してaaaaください");
        }
        if(password.length() == 0) {
            errorList.add("パスワードを入力してください");
        }
        if(!errorList.isEmpty()) {
            model.addAttribute("errorLists",errorList);
            return "login";
        }

        List<Account> accountList = accountrepository.findByEmailAndPassword(email,password);
        if (accountList == null || accountList.size() == 0) {
            // 存在しなかった場合
            model.addAttribute("errorLists", "メールアドレスとパスワードが一致しませんでした");
            return "login";
        }
        Account account = accountList.get(0);
        //セッション管理されたアカウント情報に名前をセット
        session.setAttribute("userName", account.getUserName());
        session.setAttribute("userId", account.getUserId());
        //TODO 貸出物一覧に変更
        return "redirect:/notice";
        ////[/lendItems]へリダイレクト
        //return "redirect:/lendItems";
    }

}
