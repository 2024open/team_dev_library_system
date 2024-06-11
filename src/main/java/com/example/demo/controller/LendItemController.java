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
import com.example.demo.entity.Genre;
import com.example.demo.entity.LendItemDetail;
import com.example.demo.entity.Status;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.AccountRepository;
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

		if (category == null) {
			lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
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
	
	
	@GetMapping({ "/lendItems/{id}" })
	public String detail(
			@PathVariable("id") Integer id,
			Model model) {
		
		LendItemDetail lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(id).get(0);
		model.addAttribute("lendItemDetail", lendItemDetail);
		return "lendItemDetail";
	}
}
