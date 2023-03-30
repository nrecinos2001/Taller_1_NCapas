package com.example.demo.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.dto.LoginDto;
import com.example.demo.models.entities.User;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
	
	private static List<User> users = new ArrayList<>();
    
    static {
        users.add(new User("AH000011", "pass", "Arturo", "Hernandez", "user", true, new Date(10102020)));
        users.add(new User("AO000022", "pass", "Alejandro", "Ortega", "user", false, new Date(10102021)));
        users.add(new User("JP000033", "pass", "Josue", "Perez", "user", true, new Date(15102022)));
        users.add(new User("NR000044", "pass", "Nestor", "Recinos", "admin", true, new Date(10102019)));
    }
    
	@GetMapping("/login-web")
	public String getLogIn() {
		return "login";
	}
	
	@PostMapping("/login")
	public String logIn(@ModelAttribute LoginDto loginDto, Model model) {
		String pageToShow = "not-found";
		for (User user: users) {
			if(user.getCode().equals(loginDto.getCode()) && user.getPassword().equals(loginDto.getPassword())) {
				pageToShow = "dashboard";
				model.addAttribute("users", users);
			}
		}	
		return pageToShow;
	}
}
