package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.UserDto;
import com.salonspa.example.entity.User;
import com.salonspa.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto saveUser(UserDto userdto) {
		User user = new User();
		user.setUserId(userdto.getUserId());
		user.setUserName(userdto.getUserName());
		user.setPhoneNumber(userdto.getPhoneNumber());
		user.setAddress(userdto.getAddress());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		userRepository.save(user);
         return userdto; 
    }

	@Override
    public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();

		return users.stream()
				.map(user -> new UserDto(user.getUserId(),user.getUserName(),user.getAddress(),user.getPhoneNumber(),user.getEmail(),user.getPassword()))
				.collect(Collectors.toList());
    }

	@Override
	public UserDto deleteUser(int id) {
		User user = userRepository.findById(id).orElse(null);
		UserDto userDto = new UserDto();
		if (user != null) {
			userDto.setUserId(user.getUserId());
			userDto.setUserName(user.getUserName());
			userDto.setAddress(user.getAddress());
			userDto.setPhoneNumber(user.getPhoneNumber());
			userDto.setEmail(user.getEmail());
			userDto.setPassword(user.getPassword());
		}
		userRepository.deleteById(id);
		return userDto;
	}


}
