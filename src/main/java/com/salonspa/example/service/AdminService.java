package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.AdminDto;

public interface AdminService {
	public AdminDto saveAdmin(AdminDto admindto);

    public List<AdminDto> getAdmins(); 

    public AdminDto deleteAdmin(int id); 
    
}
