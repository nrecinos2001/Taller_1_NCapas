package com.example.demo.controllers;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        users.add(new User("AH000011", "pass", "Arturo", "Hernandez", "user", true, new Date("10/10/2020")));
        users.add(new User("AO000022", "pass", "Alejandro", "Ortega", "user", false, new Date("10/10/2021")));
        users.add(new User("JP000033", "pass", "Josue", "Perez", "user", true, new Date("11/10/2024")));
        users.add(new User("NR000044", "pass", "Nestor", "Recinos", "admin", true, new Date("10/12/2019")));
    }
    
	@GetMapping("/login-web")
	public String getLogIn() {
		return "login";
	}
	
	@SuppressWarnings("deprecation")
	@PostMapping("/login")
	public String logIn(@ModelAttribute LoginDto loginDto, Model model) {		
		for (User user: users) {
			if(user.getCode().equals(loginDto.getCode()) && user.getPassword().equals(loginDto.getPassword())) {
				LocalDateTime actualDate = LocalDateTime.now();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			    String formattedDate = actualDate.format(formatter);
			     
				if (user.getHired().after(new Date(formattedDate))) {
					model.addAttribute("code", "403");
					model.addAttribute("message", "El usuario todavia no ha iniciado labores");
					return "not-found";
				}
				if (user.getRole().equals("admin")) {
					model.addAttribute("admin", user);
					model.addAttribute("users", users);
					return "admin-dashboard";
				} else {
					model.addAttribute("user", user);
					model.addAttribute("date", formattedDate);
					return "user-dashboard";
				}
			}
		}
		model.addAttribute("code", "404");
		model.addAttribute("message", "El usuario no fue encontrado");
		return "not-found";
	}
}
