package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class PCSMappingDto {
	private int id;
	private int productid ;
	private int colourid;
	private int sizeid;
	private ShrinkedProductDto shrinkedProductDto;
	private ColourDto colourDto;
	private SizeDto sizeDto;
	private Status status;
}
