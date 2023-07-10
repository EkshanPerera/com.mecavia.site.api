package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.MaterialOutNoteDto;

public interface MaterialOutNoteController {
	@GetMapping("/getmaterialoutnotes")
	ResponseEntity<ResponseDto> getMaterialOutNotes();
	@PutMapping("/updatematerialoutnote")
	ResponseEntity<ResponseDto> updateMaterialOutNote(@RequestBody MaterialOutNoteDto materialOutNoteDto);
	@GetMapping("/getmaterialoutnote/{materialoutnoteid}")
	ResponseEntity<ResponseDto> getMaterialOutNote(@PathVariable String materialOutNoteid);
	@PostMapping("/savematerialoutnote")
	ResponseEntity<ResponseDto> saveMaterialOutNote(@RequestBody MaterialOutNoteDto materialOutNoteDto);
	@PostMapping("/activeinactivematerialoutnote")
	ResponseEntity<ResponseDto> activeinactiveMaterialOutNote(@RequestBody ActiveInactiveEntityDto aiedto);
}
