package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AllUserLendList;
import com.example.demo.repository.ALLUserLendListRepoitory;

@Controller
public class SuperUserController {

	@Autowired
	ALLUserLendListRepoitory allUserLendListrepository;

	@GetMapping({ "/", "/home" })
	public String index(
			@RequestParam(value = "category", defaultValue = "") Integer category,
			Model model) {
		List<AllUserLendList> lendItemList = null;

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

}
