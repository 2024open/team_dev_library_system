package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Notice;
import com.example.demo.model.SuperUser;
import com.example.demo.repository.NoticeRepository;

@Controller
public class NoticeController 
{
    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    SuperUser user;
    
    //お知らせ一覧表示
    @GetMapping("/notice")
    public String index(
            @RequestParam(value = "noticeId", defaultValue = "") Integer noticeId,
            Model model) 
    {
        //お知らせ一覧を取得
    	//TODO LibraryId取りたい
        List<Notice> noticeList = noticeRepository.findByUserIdAndLibraryId(2,1);
        
        Integer userId = user.getUserId();
        
        model.addAttribute("userId", userId);
        
        model.addAttribute("noticeList", noticeList);
        return "notice";
    }
    //お知らせ詳細表示
    @GetMapping("/notice/{id}")
    public String detail(
            @PathVariable("id") Integer id,
            Model model) 
    {
        //NoticeテーブルをID(主キー)で検索
        Notice notice = noticeRepository.findById(id).get();
        model.addAttribute("notice", notice);
        return "noticeDetail";
    }
}
