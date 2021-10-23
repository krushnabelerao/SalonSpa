package com.salonspa.example.service;

import java.util.List;

import com.salonspa.example.dto.BeauticianDto;

public interface BeauticianService {

	public BeauticianDto saveBeautician(BeauticianDto beauticianDto);

    public List<BeauticianDto> getBeauticians();
	
    public BeauticianDto deleteBeautician(int id);
	
}
