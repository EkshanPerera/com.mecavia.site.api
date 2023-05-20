package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.FinishedGoodsInNoteDto;

public interface FinishedGoodsInNoteController {
	@GetMapping("/getfinishedgoodsinnotes")
	ResponseEntity<ResponseDto> getFinishedGoodsInNotes();
	@PutMapping("/updatefinishedgoodsinnote")
	ResponseEntity<ResponseDto> updateFinishedGoodsInNote(@RequestBody FinishedGoodsInNoteDto finishedGoodsInNoteDto);
	@GetMapping("/getfinishedgoodsinnote/{finishedgoodsinnoteid}")
	ResponseEntity<ResponseDto> getFinishedGoodsInNote(@PathVariable String finishedGoodsInNoteid);
	@PostMapping("/savefinishedgoodsinnote")
	ResponseEntity<ResponseDto> saveFinishedGoodsInNote(@RequestBody FinishedGoodsInNoteDto finishedGoodsInNoteDto);
	@PostMapping("/activeinactivefinishedgoodsinnote")
	ResponseEntity<ResponseDto> activeinactiveFinishedGoodsInNote(@RequestBody ActiveInactiveEntityDto aiedto);
}
