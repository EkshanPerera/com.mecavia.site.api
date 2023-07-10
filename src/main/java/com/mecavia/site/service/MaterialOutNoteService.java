package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialOutNoteDto;

public interface MaterialOutNoteService {
	String saveMaterialOutNote(MaterialOutNoteDto materialOutNotedto) throws Exception;
	List<MaterialOutNoteDto> getMaterialOutNotes();
	String updateMaterialOutNote(MaterialOutNoteDto materialOutNotedto);
	MaterialOutNoteDto getMaterialOutNote(String materialOutNoteid);
	String activeinactiveMaterialOutNote(ActiveInactiveEntityDto aiedto);
}
