package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto user);

    public List<UserDto> getUsers();

    public UserDto deleteUser(int id);

}
