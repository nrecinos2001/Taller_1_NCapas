package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.dto.LoginDto;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	@PostMapping("/login")
	public String logIn(@ModelAttribute LoginDto loginDto) {
		return "Hola";
	}
}
