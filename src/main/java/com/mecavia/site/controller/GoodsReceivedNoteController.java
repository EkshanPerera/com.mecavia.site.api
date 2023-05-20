package com.mecavia.site.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.dto.GoodsReceivedNoteDto;

public interface GoodsReceivedNoteController {
	@GetMapping("/getgoodsreceivednotes")
	ResponseEntity<ResponseDto> getGoodsReceivedNotes();
	@PutMapping("/updategoodsreceivednote")
	ResponseEntity<ResponseDto> updateGoodsReceivedNote(@RequestBody GoodsReceivedNoteDto goodsReceivedNoteDto);
	@GetMapping("/getgoodsreceivednote/{goodsreceivednoteid}")
	ResponseEntity<ResponseDto> getGoodsReceivedNote(@PathVariable String goodsReceivedNoteid);
	@PostMapping("/savegoodsreceivednote")
	ResponseEntity<ResponseDto> saveGoodsReceivedNote(@RequestBody GoodsReceivedNoteDto goodsReceivedNoteDto);
	@PostMapping("/activeinactivegoodsreceivednote")
	ResponseEntity<ResponseDto> activeinactiveGoodsReceivedNote(@RequestBody ActiveInactiveEntityDto aiedto);
}
