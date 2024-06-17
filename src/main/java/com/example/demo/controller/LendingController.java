package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.SuperUser;
import com.example.demo.repository.LendingItemRepository;
import com.example.demo.service.LendingService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LendingController {
	//セッション系
	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	//サービス
	@Autowired
	LendingService lendingService;

	//リポジトリ
	@Autowired
	LendingItemRepository lendingItemRepository;

	@GetMapping("/lending")
	public String index(Model model) {

		lendingService.forLendingItemList(model, "lendingItemList");
		return "lendingList";
	}

	//貸出履歴
	@GetMapping("/lendingHistory")
	public String history(Model model) {

		lendingService.forLendingHistory(model, "lendingHistoryList");
		return "lendingHistory";
	}

}
