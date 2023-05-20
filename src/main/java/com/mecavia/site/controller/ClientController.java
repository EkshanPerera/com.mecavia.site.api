package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.ClientDto;

public interface ClientController {
	@GetMapping("/getclients")
	ResponseEntity<ResponseDto> getClients();
	@PutMapping("/updateclient")
	ResponseEntity<ResponseDto> updateClient(@RequestBody ClientDto clientDto);
	@GetMapping("/getclient/{clientid}")
	ResponseEntity<ResponseDto> getClient(@PathVariable String clientid);
	@PostMapping("/saveclient")
	ResponseEntity<ResponseDto> saveClient(@RequestBody ClientDto clientDto);
	@PostMapping("/activeinactiveclient")
	ResponseEntity<ResponseDto> activeinactiveClient(@RequestBody ActiveInactiveEntityDto aiedto);
	
}
