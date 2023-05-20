package com.mecavia.site.serviceimplies;

import java.util.List;



import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ColourDto;
import com.mecavia.site.entity.Colour;
import com.mecavia.site.repo.ColourRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class ColourService implements com.mecavia.site.service.ColourService {
	@Autowired
	private ColourRepo colourRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	

	//save color
	@Override
	public String saveColour(ColourDto colourDto) {
		if(colourRepo.existsById(colourDto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			colourRepo.save(modelMapper.map(colourDto, Colour.class));
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<ColourDto> getColours(){
		List<Colour> colourList  = colourRepo.findAll();
		return modelMapper.map(colourList,new TypeToken<List<ColourDto>>(){}.getType());
	}
	@Override
	public String updateColours(ColourDto colourDto) {
		if(colourRepo.existsById(colourDto.getId())) {
			colourRepo.save(modelMapper.map(colourDto, Colour.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}
	@Override
	public ColourDto getColour(String colourid) {
		Colour colour =  colourRepo.findByid(Integer.parseInt(colourid));
		if(colour == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(colour,ColourDto.class);
		}
	}
	@Override
	public String activeinactiveColour(ActiveInactiveEntityDto aiedto) {
		if(colourRepo.existsById(aiedto.getId())) {
			int res = colourRepo.activeinactiveColour(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}	
	
	public String deleteColour(ColourDto colourDto) {
		if(colourRepo.existsById(colourDto.getId())) {
			colourRepo.delete(modelMapper.map(colourDto, Colour.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}


}
