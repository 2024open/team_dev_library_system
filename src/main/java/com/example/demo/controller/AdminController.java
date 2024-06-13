package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Controller
public class AdminController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/admin/lenditems")
    public String lendItem() {
        // 処理は後で書く
        return "";
    }
    
    @GetMapping("/admin/home")
    public String home() {
        // 処理は後で書く
        return "";
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
    
    //ban処理やるよ
    @PostMapping("/admin/accountManager")
    public String ban(
    		//@RequestParam(value ="ban",defaultValue = "") boolean ban,
    		Model model) {
    	//accountRepository.save(account);
        return "redirect:/admin/accountManager";
    }
}
