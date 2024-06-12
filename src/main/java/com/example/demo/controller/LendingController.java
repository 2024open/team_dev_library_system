package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.LendingItem;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.LendingItemRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LendingController {

	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	@Autowired
	LendingItemRepository lendingItemRepository;

	@GetMapping("/lending")
	public String index(Model model) {
		List<LendingItem> lendingItemList = lendingItemRepository.sqlLendingList(superUser.getUserId());
		model.addAttribute("lendingItemList", lendingItemList);
		return "lendingList";
	}

	//	@GetMapping("/lendingHistory")
	//	public String history() {
	//
	//	}

}
