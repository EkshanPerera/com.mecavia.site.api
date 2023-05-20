package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.GoodsReceivedNoteDto;

public interface GoodsReceivedNoteService {
	String saveGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNotedto);
	List<GoodsReceivedNoteDto> getGoodsReceivedNotes();
	String updateGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNotedto);
	GoodsReceivedNoteDto getGoodsReceivedNote(String goodsReceivedNoteid);
	String activeinactiveGoodsReceivedNote(ActiveInactiveEntityDto aiedto);
}
