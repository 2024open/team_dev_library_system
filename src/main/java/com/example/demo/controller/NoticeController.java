package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String index(Model model) {
    		List<Notice> noticeList = noticeRepository.findAll();
    		model.addAttribute("notices", noticeList);
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
