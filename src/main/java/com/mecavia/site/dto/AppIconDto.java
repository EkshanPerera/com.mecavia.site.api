package com.mecavia.site.dto;

import com.mecavia.site.entity.AppIconGroup;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppIconDto {
	private int id;
	private String code;
	private String description;
	private Status status;
	private AppIconGroup appIconGroup;
}
