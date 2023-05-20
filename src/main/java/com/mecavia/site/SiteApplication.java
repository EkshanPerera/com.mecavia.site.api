package com.mecavia.site;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mecavia.site.util.VarList;

@SpringBootApplication()
@EnableTransactionManagement
public class SiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SiteApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public VarList varList() {
		ZoneId zoneId = ZoneId.of("Asia/Colombo");
		ZonedDateTime zdt = LocalDate.now(zoneId).atTime(LocalTime.now()).atZone(zoneId);
		return new VarList(zdt);
	}
}
