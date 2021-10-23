package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.AdminDto;
import com.salonspa.example.dto.UserDto;
import com.salonspa.example.entity.Admin;
import com.salonspa.example.entity.User;
import com.salonspa.example.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepo;
	
	@Override
	public AdminDto saveAdmin(AdminDto admindto) {
		Admin admin = new Admin();
		admin.setAdminId(admindto.getAdminId());
		admin.setUserName(admindto.getUserName());
		admin.setPhoneNumber(admindto.getPhoneNumber());
		admin.setAddress(admindto.getAddress());
		admin.setEmail(admindto.getEmail());
		admin.setPassword(admindto.getPassword());
		adminRepo.save(admin);
         return admindto; 
    }

	@Override
    public List<AdminDto> getAdmins() {
		List<Admin> admin = adminRepo.findAll();

		return admin.stream()
				.map(adminDto -> new AdminDto(adminDto.getAdminId(),adminDto.getUserName(),
						adminDto.getAddress(),adminDto.getPhoneNumber(),adminDto.getEmail(),adminDto.getPassword()))
				.collect(Collectors.toList());
    }

	@Override
	public AdminDto deleteAdmin(int id) {
		Admin admin = adminRepo.findById(id).orElse(null);
		AdminDto adminDto = new AdminDto();
		if (admin != null) {
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setUserName(admin.getUserName());
			adminDto.setAddress(admin.getAddress());
			adminDto.setPhoneNumber(admin.getPhoneNumber());
			adminDto.setEmail(admin.getEmail());
			adminDto.setPassword(admin.getPassword());
		}
		adminRepo.deleteById(id);
		return adminDto;
	}
	
}
