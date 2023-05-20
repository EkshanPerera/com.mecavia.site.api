package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ColourDto;

public interface ColourService {
	String saveColour(ColourDto colourDto);
	 List<ColourDto> getColours();
	 String updateColours(ColourDto colourDto);
	 ColourDto getColour(String colourid);
	 String activeinactiveColour(ActiveInactiveEntityDto aiedto);
}
