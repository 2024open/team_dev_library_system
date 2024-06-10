package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuperUserController {

	@GetMapping({ "/", "/home" })
	public String index() {
		return "testFile";
	}

}
