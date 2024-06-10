package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.NoticeRepository;

@Controller
public class NoticeController {
@Autowired
NoticeRepository noticeRepository;
////TODO こいつエラー出る
//@Autowired
//Library library;

	//お知らせ一覧表示
	@GetMapping("/notice")
	public String index(
			@RequestParam(value = "noticeId",defaultValue = "") Integer noticeId,
			Model model) {
		//お知らせ一覧を取得しているつもり
		//List<Notice>noticeList = noticeRepository.findByNoticeIdAndLibraryId(noticeId,library.getId());
		
		//model.addAttribute("notice",noticeList);
		return "notice";
	}
}
