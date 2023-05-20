package com.mecavia.site.dto;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UOMDto {
	private int id;
	private String code;
	private String scode;
	private String description;
	private Status status;	
}
