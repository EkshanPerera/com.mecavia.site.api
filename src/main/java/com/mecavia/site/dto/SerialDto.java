package com.mecavia.site.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SerialDto {
	private int id;
	private String servicecode;
	private int serviceId;
	private int value;
}
