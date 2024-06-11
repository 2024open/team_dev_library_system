package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Notice;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.NoticeRepository;

import jakarta.servlet.http.HttpSession;



import com.example.demo.entity.Account;
import com.example.demo.entity.AdminLendList;
import com.example.demo.entity.AdminLendRoom;
import com.example.demo.entity.LendItem;
import com.example.demo.entity.LendingItem;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.AdminLendListRepoitory;
import com.example.demo.repository.AdminLendRoomRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.LendItemRepository;
import com.example.demo.repository.LendingItemRepository;
import com.example.demo.service.Common;
import com.example.demo.service.LibrarianService;

@Controller
public class LibrarianController {

	@Autowired
	HttpSession session;
	
	@Autowired
	SuperUser superUser;
	
  @Autowired
	AccountRepository accountRepository;

	@Autowired
	LendItemRepository lendItemRepository;

	@Autowired
	NoticeRepository noticeRepository;

	@Autowired
	LendingItemRepository lendingItemRepository;

	@Autowired
	AdminLendListRepoitory adminLendListRepository;
  
  // お知らせ一覧表示
	@GetMapping("/librarian/notice")
	public String notice(Model model) {

		List<Notice> noticeList = noticeRepository.findAll();
		
		model.addAttribute("notices", noticeList);
    return "noticeAdmin";
	}

	// お知らせ新規登録画面の表示
	@GetMapping("/notice/add")
	public String create() {
		return "addNotice";
	}

	// 新規登録処理
	@PostMapping("/notice/add")
	public String store(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "content", defaultValue = "") String content,
			Model model) {
		
		Integer userId =superUser.getLibraryId();
		Integer libraryId =superUser.getUserId();
		

		// Noticeオブジェクトの生成
		Notice notice = new Notice(libraryId, userId, title, content);
		// noticeテーブルへの反映（INSERT）
		
		noticeRepository.save(notice);
		return "redirect:/librarian/notice";
	}

	// お知らせ更新画面表示
	@GetMapping("/notice/{noticeId}/edit")
	public String edit(
      @PathVariable("noticeId") Integer noticeId,
			Model model) {

		// itemsテーブルをID（主キー）で検索
		Notice notice = noticeRepository.findById(noticeId).get();
		model.addAttribute("notice", notice);
		return "editNotice";
	}

	// お知らせ更新処理
	@PostMapping("/notice/{noticeId}/edit")
	public String update(
			@PathVariable("noticeId") Integer noticeId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "content", defaultValue = "") String content,
			Model model) {
    
    Integer userId =superUser.getLibraryId();
		Integer libraryId =superUser.getUserId();
		Notice notice = new Notice(noticeId,libraryId, userId, title, content);
	
		noticeRepository.save(notice);
		
		return "redirect:/librarian/notice";
  }

	//TODO
	//貸出物更新処理
	@PostMapping("/librarian/lenditems/{id}/edit")
	public String update(
			@PathVariable("id") String lendItemIdStr,
			Model model) {
		return "redirect:/librarian/lenditems/{id}/edit";
   }

	// お知らせ削除処理
	@PostMapping("/items/{noticeId}/delete")
	public String delete(@PathVariable("noticeId") Integer noticeId, Model model) {

		noticeRepository.deleteById(noticeId);

		return "redirect:/librarian/notice";
	}
}

