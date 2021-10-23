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

import com.salonspa.example.dto.AdminDto;
import com.salonspa.example.service.AdminService;

@RestController
@RequestMapping("/adminservice")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/add")
	public AdminDto saveAdmin(@Valid @RequestBody AdminDto admin) {
        return adminService.saveAdmin(admin);
    }

	@GetMapping("/getall")
    public List<AdminDto> getAdmins() {
        return adminService.getAdmins();
    }

	@DeleteMapping("/delete/{id}")
    public AdminDto deleteAdmin(@PathVariable(value = "id") int id) {
        return adminService.deleteAdmin(id);
    }
	
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<AdminDto> deleteAdmin(@PathVariable(value = "id") int id) {
//		return new ResponseEntity<AdminDto>(adminService.deleteAdmin(id), HttpStatus.OK);
//
//	}
}
