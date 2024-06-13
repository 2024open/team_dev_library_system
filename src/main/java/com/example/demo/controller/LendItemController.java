package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.AllUserLendList;
import com.example.demo.entity.Category;
import com.example.demo.entity.DetailIf;
import com.example.demo.entity.Genre;
import com.example.demo.entity.LendItemDetail;
import com.example.demo.entity.Status;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DetailIfRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.StatusRepository;

@Controller
public class LendItemController {
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ALLUserLendListRepoitory allUserLendListrepository;

	@Autowired
	GenreRepository genreRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	ALLUserLendDetailRepoitory alluserLendDetailRepoitory;

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	DetailIfRepository detailIfRepository;
	
	@Autowired
	SuperUser user;
	
	//貸出物一覧表示
	@GetMapping({ "/lendItems" })
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

		String titleMsg = "";

		if (category == null) {
			lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
			titleMsg = "本一覧";
			model.addAttribute("titleMsg", titleMsg);
		} else if (category == 1) {
			lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
			titleMsg = "本一覧";
			model.addAttribute("titleMsg", titleMsg);
		} else if (category == 2) {
			lendItemList = allUserLendListrepository.sqlALLUserCDLendJoin();
			titleMsg = "CD一覧";
			model.addAttribute("titleMsg", titleMsg);
		} else if (category == 3) {
			lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoin();
			titleMsg = "DVD一覧";
			model.addAttribute("titleMsg", titleMsg);
		} else if (category == 4) {
			lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoin();
			titleMsg = "紙芝居一覧";
			model.addAttribute("titleMsg", titleMsg);
		}
		model.addAttribute("lendItemList", lendItemList);

		Integer userId = user.getUserId();
		model.addAttribute("userId", userId);
		
		return "index";
	}

	//詳細表示
	@GetMapping({ "/lendItems/{id}" })
	public String detail(
			@PathVariable("id") Integer id,
			Model model) {

		//Map
		Map<Integer, String> genreMap = new HashMap<>();

		List<Genre> genreMapList = genreRepository.findAll();

		//Mapで格納
		for (Genre genre : genreMapList) {

			genreMap.put(genre.getGenreId(), genre.getGenreName());

		}

		Map<Integer, String> categoryMap = new HashMap<>();

		List<Category> categoryMapList = categoryRepository.findAll();

		//Mapで格納
		for (Category categoryList : categoryMapList) {

			categoryMap.put(categoryList.getCategoryId(), categoryList.getCategoryName());

		}

		Map<Integer, String> statusMap = new HashMap<>();

		List<Status> statusMapList = statusRepository.findAll();

		//Mapで格納
		for (Status status : statusMapList) {

			statusMap.put(status.getStatusId(), status.getStatusName());

		}

		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("genreMap", genreMap);
		model.addAttribute("statusMap", statusMap);
		//Map
		
		
//		LendItemDetail lendItemDetailIf = alluserLendDetailRepoitory.sqlALLUserDVDLendJoinDetail(id).get(0);

		DetailIf Detailif = detailIfRepository.findByLendItemId(id).get(0);
		
		LendItemDetail lendItemDetail = null;

		if (Detailif.getCategoryId()==1) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(id).get(0);
			model.addAttribute("lendItemDetail", lendItemDetail);
		} else if (Detailif.getCategoryId() == 2) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserCDLendJoinDetail(id).get(0);
			model.addAttribute("lendItemDetail", lendItemDetail);
		} else if (Detailif.getCategoryId() == 3) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserDVDLendJoinDetail(id).get(0);
			model.addAttribute("lendItemDetail", lendItemDetail);
		} else if (Detailif.getCategoryId() == 4) {
			lendItemDetail = alluserLendDetailRepoitory.sqlALLUserKamishibaiLendJoinDetail(id).get(0);
			model.addAttribute("lendItemDetail", lendItemDetail);
		}
		
		Integer userId = user.getUserId();
		model.addAttribute("userId", userId);

		return "lendItemDetail";
	}
}
