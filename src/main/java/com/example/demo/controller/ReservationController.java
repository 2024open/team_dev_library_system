package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Genre;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendItemDetail;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.ReservationDetail;
import com.example.demo.entity.Status;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.repository.LendItemSaveRepository;
import com.example.demo.repository.ReservationDetailRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.StatusRepository;

@Controller
public class ReservationController {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ALLUserLendListRepoitory allUserLendListrepository;
	
	
	@Autowired
	ALLUserLendDetailRepoitory alluserLendDetailRepoitory;
	
	@Autowired
	ReservationRepository reservationRepoitory;
	
	@Autowired
	LendItemSaveRepository lendItemSaveRepository;
	

	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ReservationDetailRepository reservationDetailRepository;
	
	@Autowired
	SuperUser superUser;
	
	//予約一覧
	@GetMapping({ "/reservation" })
	public String index(Model model) {

		Integer userId=3;
		
		List<ReservationDetail> reservation = reservationDetailRepository.sqlReservationLendJoin(userId);
		
		model.addAttribute("reservation",reservation);
		
		return "reservationList";
	}
	
	
	//予約
	@PostMapping({ "/reservation/add" })
	public String reserve(
			@RequestParam(value = "reservation", defaultValue = "") Integer reservation,
			LocalDateTime localdatetime,
			Model model) {
		
		LendItemDetail lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(reservation).get(0);
		
		LendItem lenditem = lendItemSaveRepository.findByLendItemId(reservation);
		
		
		Integer userId=3;
		
		Integer status=3;
		
		
		if(lenditem.getStatusId()==1) {
		Reservation reserv =new Reservation(lendItemDetail.getLendItemId(),userId);
		
		LendItem lenditemsave =new LendItem(reservation,lenditem.getLibraryid(),lenditem.getCategoryId(),localdatetime,status,lenditem.getAnyId());

		lendItemSaveRepository.save(lenditemsave);
		
		reservationRepoitory.save(reserv);
		return "redirect:/lendItems";
		}else {
			
			Map<Integer, String> genreMap = new HashMap<>();

			List<Genre> genreMapList = genreRepository.findAll();

			//Mapで格納
			for (Genre genre : genreMapList) {

				genreMap.put(genre.getGenreId(), genre.getGenreName());

			}
			
			Map<Integer, String> categoryMap = new HashMap<>();

			List<Category> categoryMapList = categoryRepository.findAll();

			//Mapで格納
			for (Category category : categoryMapList) {

				categoryMap.put(category.getCategoryId(), category.getCategoryName());

			}
			
			Map<Integer, String> statusMap = new HashMap<>();

			List<Status> statusMapList = statusRepository.findAll();

			//Mapで格納
			for (Status statusM : statusMapList) {

				statusMap.put(statusM.getStatusId(), statusM.getStatusName());

			}

			
			model.addAttribute("categoryMap", categoryMap);
			model.addAttribute("genreMap", genreMap);
			model.addAttribute("statusMap", statusMap);
			String msg="この貸出物は予約できません";
			model.addAttribute("msg",msg);
			model.addAttribute("lendItemDetail", lendItemDetail);
			return "lendItemDetail";
		}
	}
	
}
