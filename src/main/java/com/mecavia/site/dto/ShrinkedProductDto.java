package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShrinkedProductDto {
	private int id;
	private String code;
	private String name;
	private String img1URL;
	private Status status;
}
