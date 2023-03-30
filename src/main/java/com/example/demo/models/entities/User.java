package com.example.demo.models.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
	private String code;
	private String name;
	private String lastname;
	private String role;
	private Boolean isActive;
	private Date hired;
}
