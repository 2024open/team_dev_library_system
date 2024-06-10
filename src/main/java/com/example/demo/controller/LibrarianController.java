package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AdminLendList;
import com.example.demo.entity.Category;
import com.example.demo.repository.AdminLendListRepoitory;
import com.example.demo.repository.CategoryRepository;

@Controller
public class LibrarianController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AdminLendListRepoitory adminLendListRepository;

	@GetMapping("/librarian/lenditems")
	public String lendItem(
			@RequestParam(name = "libraryId", defaultValue = "1") String libraryIdStr,
			@RequestParam(name = "categoryId", defaultValue = "1") String categoryIdStr,
			@RequestParam(name = "genreId", defaultValue = "") String genreIdStr,
			Model model) {

		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categoryList", categoryList);

		//		if (!(Common.isParceInt(libraryIdStr) && Common.isParceInt(categoryIdStr) && Common.isParceInt(genreIdStr))) {
		//			return "redirect:/su/home";
		//		}

		List<AdminLendList> LendJoinAny = new ArrayList<AdminLendList>();
		Integer libraryId = Integer.parseInt(libraryIdStr);
		Integer categoryId = Integer.parseInt(categoryIdStr);
		//		Integer genreId = Integer.parseInt(genreIdStr);

		switch (categoryId) {
		case 1:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinBook(libraryId);
			break;
		case 2:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinCD(libraryId);
			break;
		case 3:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinDVD(libraryId);
			break;
		case 4:
			LendJoinAny = adminLendListRepository.sqlAdminLendJoinKamishibai(libraryId);
			break;
		}
		model.addAttribute("LendJoinAny", LendJoinAny);
		return "librarianLendItems";
	}

}
