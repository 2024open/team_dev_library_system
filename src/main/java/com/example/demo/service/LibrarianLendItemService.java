package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.AdminLendList;
import com.example.demo.entity.AdminLendRoom;
import com.example.demo.repository.AdminLendListRepository;
import com.example.demo.repository.AdminLendRoomRepository;

@Service
public class LibrarianLendItemService {

	//自作
	@Autowired
	AdminLendListRepository adminLendListRepository;

	@Autowired
	AdminLendRoomRepository adminLendRoomRepository;

	public void forLendItemList(Model model, Integer categoryId, Integer libraryId) {
		List<AdminLendList> LendJoinAny = new ArrayList<AdminLendList>();
		LendJoinAny = null;
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
		case 5:
			List<AdminLendRoom> LendJoinRoom = new ArrayList<AdminLendRoom>();
			LendJoinRoom = adminLendRoomRepository.sqlAdminLendJoinRoom(libraryId);
			model.addAttribute("LendJoinRoom", LendJoinRoom);
			break;
		}
		model.addAttribute("LendJoinAny", LendJoinAny);
	}

}
