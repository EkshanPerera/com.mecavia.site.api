package com.mecavia.site.dto;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDto {
	private int id;
	private String fromEmail;
	private String toEmail;
	private String subject;
	private String body;
	private Status status;
}
