package com.example.Gym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String test() {
		return "login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/regist")
	public String regist() {
		return "regist";
	}
	@GetMapping("/writeQna")
	public String writeQna() {
		return "writeQna";
	}
	@GetMapping("/answerQna")
	public String answerQna() {
		return "answerQna";
	}
}