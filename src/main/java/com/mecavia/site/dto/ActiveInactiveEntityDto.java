package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActiveInactiveEntityDto {
	private int id;
	private String Code;
	private Status status;
}
