package com.salonspa.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salonspa.example.dto.BeauticianDto;
import com.salonspa.example.entity.Beautician;
import com.salonspa.example.repository.BeauticianRepository;

@Service
public class BeauticianServiceImpl implements BeauticianService {
	@Autowired
	private BeauticianRepository beauticianRepository;

	@Override
	public BeauticianDto saveBeautician(BeauticianDto beauticianDto) {
		Beautician beautician = new Beautician();
		beautician.setBeauticianId(beauticianDto.getBeauticianId());
		beautician.setBeauticianName(beauticianDto.getBeauticianName());
		beautician.setExperience(beauticianDto.getExperience());
		beautician.setEmail(beauticianDto.getEmail());
		beauticianRepository.save(beautician);
         return beauticianDto;
    }

	@Override
    public List<BeauticianDto> getBeauticians() {
		List<Beautician> beauticians = beauticianRepository.findAll();

		return beauticians.stream()
				.map(beauticianDto -> new BeauticianDto(beauticianDto.getBeauticianId(),beauticianDto.getBeauticianName(),
						beauticianDto.getPhonenumber(),beauticianDto.getEmail(),beauticianDto.getExperience()					
						))
				.collect(Collectors.toList());
    }

	@Override
    public BeauticianDto deleteBeautician(int id) {
		Beautician beautician = beauticianRepository.findById(id).orElse(null);
		BeauticianDto beauticianDto = new BeauticianDto();
		if (beautician != null) {
			beauticianDto.setBeauticianId(beautician.getBeauticianId());
			beauticianDto.setBeauticianName(beautician.getBeauticianName());
			beauticianDto.setExperience(beautician.getExperience());
			beauticianDto.setEmail(beautician.getEmail());
		}
		beauticianRepository.deleteById(id);
		return beauticianDto;
    }
	
	
}
