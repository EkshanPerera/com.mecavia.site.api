package com.mecavia.site.entity;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockV1 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int pcsmid;
	private double openingprice;
	private ZonedDateTime openingdate;
	private int openingcount;
	private double closingprice;
	private ZonedDateTime colosingdate;
	private int colosingcount;
	private Status status;
}
