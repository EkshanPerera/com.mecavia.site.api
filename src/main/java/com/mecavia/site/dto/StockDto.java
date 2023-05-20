package com.mecavia.site.dto;

import java.time.ZonedDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {
	private int id;
	private PCSMappingDto pcsmdto;
	private int pcsmid;
	private double openingprice;
	@JsonIgnore
	private ZonedDateTime openingdate;
	private int openingcount;
	private double closingprice;
	@JsonIgnore
	private ZonedDateTime colosingdate;
	private int colosingcount;
	private Status status;
}
