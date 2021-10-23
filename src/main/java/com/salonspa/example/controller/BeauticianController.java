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

import com.salonspa.example.dto.BeauticianDto;
import com.salonspa.example.service.BeauticianService;

@RestController
@RequestMapping("/beauticianservice")
public class BeauticianController {
	@Autowired
	private BeauticianService beauticianService;
	@PostMapping("/add")
	public BeauticianDto saveBeautician(@Valid @RequestBody BeauticianDto beauticianDto) {
        return beauticianService.saveBeautician(beauticianDto);
    }

	@GetMapping("/getall")
    public List<BeauticianDto> getBeautician() {
        return beauticianService.getBeauticians();
    }
	
	@DeleteMapping("/delele/{id}")
	public BeauticianDto deleteAppointment(@PathVariable int id) {
		return beauticianService.deleteBeautician(id);
    }
	
}
