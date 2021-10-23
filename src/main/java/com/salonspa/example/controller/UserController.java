package com.salonspa.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salonspa.example.dto.UserDto;
import com.salonspa.example.service.UserService;

@RestController
@RequestMapping("/userservice")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public UserDto saveUser(@Valid @RequestBody UserDto userdto) {
        return userService.saveUser(userdto);
    }

	@GetMapping("/getall")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

	@DeleteMapping("/delete/{id}")
    public UserDto deleteUser(@PathVariable int id) {
    	return userService.deleteUser(id);
    }

}
