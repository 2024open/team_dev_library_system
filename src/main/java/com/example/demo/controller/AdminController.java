package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/lenditems")
	public String lendItem() {
		//		処理は後で書く
		return "";
	}

	@GetMapping("/admin/home")
	public String home() {
//		処理は後で書く
		return "";
	}

}
