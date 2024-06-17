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
import com.example.demo.entity.LendItemRoomDetail;
import com.example.demo.entity.RoomList;
import com.example.demo.entity.Status;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.ALLUserLendListRoomRepoitory;
import com.example.demo.repository.ALLUserLendRoomDetailRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DetailIfRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.StatusRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LendItemController {
	@Autowired
	HttpSession session;
	
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
	ALLUserLendListRoomRepoitory allUserLendListRoomRepoitory;
	
	@Autowired
	ALLUserLendRoomDetailRepoitory allUserLendRoomDetailRepoitory;
	
	@Autowired
	SuperUser user;

	//貸出物一覧表示
	@GetMapping({ "/lendItems" })
	public String index(
			@RequestParam(value = "category", defaultValue = "") Integer category,
			@RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "Possibility", defaultValue = "0") Integer Possibility,
			Model model) {
		List<AllUserLendList> lendItemList = null;
		List<RoomList>lendItemRoomList = null;

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
			//デフォルト表示
			if (search.equals("")) {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinP();
					break;
					
				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinI();
					break;
					
				default:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
				}
			} else {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinPsearch('%'+search+'%');
					break;
				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinIsearch('%'+search+'%');
					break;
				default:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinSearch('%'+search+'%');
				}
			}
			titleMsg = "本一覧";
			model.addAttribute("titleMsg", titleMsg);
			model.addAttribute("lendItemList", lendItemList);
		} else if (category == 1) {
			//本表示
			if (search.equals("")) {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinP();
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinI();
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoin();
				}
			} else {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinPsearch('%'+search+'%');
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinIsearch('%'+search+'%');
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserBookLendJoinSearch('%'+search+'%');
				}
			}
			titleMsg = "本一覧";
			model.addAttribute("titleMsg", titleMsg);
			model.addAttribute("lendItemList", lendItemList);
		} else if (category == 2) {
			//CD表示
			if (search.equals("")) {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoinP();
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoinI();
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoin();
				}
			} else {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoinPsearch('%'+search+'%');
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoinIsearch('%'+search+'%');
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserCDLendJoinSearch('%'+search+'%');
				}
			}
			titleMsg = "CD一覧";
			model.addAttribute("titleMsg", titleMsg);
			model.addAttribute("lendItemList", lendItemList);
		} else if (category == 3) {
			//DVD表示
			if (search.equals("")) {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoinP();
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoinI();
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoin();
				}
			} else {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoinPsearch('%'+search+'%');
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoinIsearch('%'+search+'%');
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserDVDLendJoinSearch('%'+search+'%');
				}
			}

			titleMsg = "DVD一覧";
			model.addAttribute("titleMsg", titleMsg);
			model.addAttribute("lendItemList", lendItemList);
		} else if (category == 4) {
			//紙芝居表示
			if (search.equals("")) {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoinP();
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoinI();
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoin();
				}
			} else {
				switch (Possibility) {
				case 1:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoinPsearch('%'+search+'%');
					break;

				case 2:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoinIsearch('%'+search+'%');
					break;

				default:
					lendItemList = allUserLendListrepository.sqlALLUserKamishibaiLendJoinSearch('%'+search+'%');
				}
			}
			titleMsg = "紙芝居一覧";
			model.addAttribute("titleMsg", titleMsg);
			model.addAttribute("lendItemList", lendItemList);
		}else if (category == 5) {
			titleMsg = "貸会議室一覧";
			model.addAttribute("titleMsg", titleMsg);
			lendItemRoomList = allUserLendListRoomRepoitory.sqlALLUserRoomLendJoin();
			model.addAttribute("lendItemRoomList", lendItemRoomList);
		}

		
		model.addAttribute("category",category);
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


		DetailIf Detailif = detailIfRepository.findByLendItemId(id).get(0);

		LendItemDetail lendItemDetail = null;
		
		LendItemRoomDetail lendItemRoomDetail =null;

		if (Detailif.getCategoryId() == 1) {
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
		}else if (Detailif.getCategoryId() == 5) {
			lendItemRoomDetail = allUserLendRoomDetailRepoitory.sqlALLUserRoomLendJoinDetail(id).get(0);
			model.addAttribute("lendItemRoomDetail", lendItemRoomDetail);
		}

		Integer userId = user.getUserId();
		model.addAttribute("userId", userId);

		return "lendItemDetail";
	}
}
