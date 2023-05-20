package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.FinishedGoodsInNoteDto;

public interface FinishedGoodsInNoteService {
	String saveFinishedGoodsInNote(FinishedGoodsInNoteDto finishedGoodsInNotedto);
	List<FinishedGoodsInNoteDto> getFinishedGoodsInNotes();
	String updateFinishedGoodsInNote(FinishedGoodsInNoteDto finishedGoodsInNotedto);
	FinishedGoodsInNoteDto getFinishedGoodsInNote(String finishedGoodsInNoteid);
	String activeinactiveFinishedGoodsInNote(ActiveInactiveEntityDto aiedto);
}
