package com.mecavia.site.dto;

import com.mecavia.site.entity.User;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDto {
	private int id;
	private String code; 
	private User enteredUser;
	private String enteredDate;
	private double totPrice;
	private Status status;
}
