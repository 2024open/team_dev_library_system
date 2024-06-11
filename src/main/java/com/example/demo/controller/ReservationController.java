package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.LendItemDetail;
import com.example.demo.entity.Reservation;
import com.example.demo.repository.ALLUserLendDetailRepoitory;
import com.example.demo.repository.ALLUserLendListRepoitory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.ReservationRepository;

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
	LendItemRepository lendItemRepository;
	

	//貸出物一覧表示
	@PostMapping({ "/reservation/add" })
	public String reserve(
			@RequestParam(value = "reservation", defaultValue = "") Integer reservation,
//			LocalDateTime localdatetime,
			Model model) {
		
		LendItemDetail lendItemDetail = alluserLendDetailRepoitory.sqlALLUserBookLendJoinDetail(reservation).get(0);
		
//		LendItem lenditem = lendItemRepository.findByLendItemId(reservation).get(0);
		
		
		Integer userId=1;
		
//		Integer status=3;
		
		Reservation reserv =new Reservation(lendItemDetail.getLendItemId(),userId);
		
//		LendItem lenditemsave =new LendItem(lenditem.getLendItemId(),lenditem.getLibraryid(),lenditem.getCategoryId(),localdatetime,status,lenditem.getAnyId());
//
//		lendItemRepository.save(lenditemsave);
		
		reservationRepoitory.save(reserv);
		
		return "redirect:/lendItems";
	}
	
}
