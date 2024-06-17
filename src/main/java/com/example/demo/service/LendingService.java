package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.ForLendingList;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.ForLendingListRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class LendingService {
	//セッション系
	@Autowired
	HttpSession session;

	@Autowired
	SuperUser superUser;

	//自作リポジトリ
	@Autowired
	ForLendingListRepository forLendingListRepository;

	//貸出中一覧で使用
	public void forLendingItemList(Model model, String address) {
		List<ForLendingList> forLendingListList = forLendingListRepository
				.sqlForLendingList(superUser.getUserId());
		model.addAttribute(address, forLendingListList);
	}

	//貸出履歴
	public void forLendingHistory(Model model, String address) {
		List<ForLendingList> forLendingHistoryList = forLendingListRepository
				.sqlForLendingHistory(superUser.getUserId());
		model.addAttribute(address, forLendingHistoryList);
	}
}
