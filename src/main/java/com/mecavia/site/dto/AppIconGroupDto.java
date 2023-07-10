package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.AppIcon;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppIconGroupDto {
	private int id;
	private String code;
	private String description;
	private List<AppIcon> appiconslist;
	private Status status;  
}
