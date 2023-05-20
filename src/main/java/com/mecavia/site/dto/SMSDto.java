package com.mecavia.site.dto;

import org.springframework.stereotype.Component;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SMSDto {
	private int id;
	private String receiver;
	private String massage;
	private Status status;
}
