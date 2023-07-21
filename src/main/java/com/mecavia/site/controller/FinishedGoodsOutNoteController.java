package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.FinishedGoodsOutNoteDto;

public interface FinishedGoodsOutNoteController {
	@GetMapping("/getfinishedgoodsoutnotes")
	ResponseEntity<ResponseDto> getFinishedGoodsOutNotes();
	@PutMapping("/updatefinishedgoodsoutnote")
	ResponseEntity<ResponseDto> updateFinishedGoodsOutNote(@RequestBody FinishedGoodsOutNoteDto finishedGoodsOutNoteDto);
	@GetMapping("/getfinishedgoodsoutnote/{finishedgoodsoutnoteid}")
	ResponseEntity<ResponseDto> getFinishedGoodsOutNote(@PathVariable String finishedGoodsOutNoteid);
	@PostMapping("/savefinishedgoodsoutnote")
	ResponseEntity<ResponseDto> saveFinishedGoodsOutNote(@RequestBody FinishedGoodsOutNoteDto finishedGoodsOutNoteDto);
	@PostMapping("/activeinactivefinishedgoodsoutnote")
	ResponseEntity<ResponseDto> activeinactiveFinishedGoodsOutNote(@RequestBody ActiveInactiveEntityDto aiedto);
}
