package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.service.UserService;



@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService service;

	
	@GetMapping("/profile")
	public ResponseEntity<UserDTO> findSelfProfile(){
		UserDTO userDTO = service.findSelfProfile();
		return ResponseEntity.ok(userDTO);
	}	
	
		
}
