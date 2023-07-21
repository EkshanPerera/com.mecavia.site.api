package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.FinishedGoodsOutNoteDto;

public interface FinishedGoodsOutNoteService {
	String saveFinishedGoodsOutNote(FinishedGoodsOutNoteDto finishedGoodsOutNotedto) throws Exception;
	List<FinishedGoodsOutNoteDto> getFinishedGoodsOutNotes();
	String updateFinishedGoodsOutNote(FinishedGoodsOutNoteDto finishedGoodsOutNotedto);
	FinishedGoodsOutNoteDto getFinishedGoodsOutNote(String finishedGoodsOutNoteid);
	String activeinactiveFinishedGoodsOutNote(ActiveInactiveEntityDto aiedto);
}
