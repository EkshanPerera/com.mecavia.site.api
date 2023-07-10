package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialOutNoteDto;
import com.mecavia.site.dto.MaterialOutNoteMaterialDto;
import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.MaterialOutNote;
import com.mecavia.site.entity.MaterialOutNoteMaterial;
import com.mecavia.site.repo.GeneralStoreRepo;
import com.mecavia.site.repo.MaterialOutNoteRepo;
import com.mecavia.site.repo.MaterialRequisitionRepo;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class MaterialOutNoteServiceImpl  implements com.mecavia.site.service.MaterialOutNoteService{
	@Autowired
	private MaterialOutNoteRepo materialOutNoterepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GeneralStoreRepo generalStoreRepo;
	
	@Autowired
	private MaterialRequisitionRepo materialRequisitionRepo;
	
	
	@Override
	public String saveMaterialOutNote(MaterialOutNoteDto materialOutNotedto) throws Exception {
		if(materialOutNoterepo.existsById(materialOutNotedto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			MaterialOutNote materialOutNote = modelMapper.map(materialOutNotedto, MaterialOutNote.class);
			List<MaterialOutNoteMaterial> materialOutNoteMaterials = new ArrayList<>();
			int i=0;
			for(MaterialOutNoteMaterialDto monMaterialDto : materialOutNotedto.getMaterialOutNoteMaterials()) {
			    MaterialOutNoteMaterial monMaterial = modelMapper.map(monMaterialDto, MaterialOutNoteMaterial.class);
			    monMaterial.setCode("MONM"+ String.format("%06d",Integer.parseInt(materialOutNote.getCode().replaceAll("[^0-9]", "").substring(2) + ++i)));
			    monMaterial.setMaterialOutNote(materialOutNote);
			    materialOutNoteMaterials.add(monMaterial);
			    Material material = monMaterialDto.getMaterial();
			    double releasedCount = monMaterialDto.getReleasedCount();
			    GeneralStore newGeneralStore =  generalStoreRepo.findByMaterial(material);
		    	newGeneralStore.setReleasedItemcount(newGeneralStore.getReleasedItemcount() + releasedCount);
		        generalStoreRepo.save(newGeneralStore);
			}
			materialOutNote.setMaterialOutNoteMaterials(materialOutNoteMaterials);
			materialOutNoterepo.save(materialOutNote);
			materialRequisitionRepo.activeinactiveMaterialRequisition(materialOutNotedto.getMaterialRequisition().getId(), materialOutNotedto.getMaterialRequisition().getCode(), Status.CLOSED.ordinal());
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<MaterialOutNoteDto> getMaterialOutNotes(){
	    List<MaterialOutNote> materialOutNoteslist = materialOutNoterepo.findAll();
	    return modelMapper.map(materialOutNoteslist,new TypeToken<List<MaterialOutNoteDto>>(){}.getType());
	}
	@Override
	public String updateMaterialOutNote(MaterialOutNoteDto materialOutNotedto) {
		if(materialOutNoterepo.existsById(materialOutNotedto.getId())) {
			materialOutNoterepo.save(modelMapper.map(materialOutNotedto, MaterialOutNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public MaterialOutNoteDto getMaterialOutNote(String materialOutNoteid) {
		MaterialOutNote MaterialOutNote =  materialOutNoterepo.findByid(Integer.parseInt(materialOutNoteid));
		if(MaterialOutNote == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(MaterialOutNote,MaterialOutNoteDto.class);
		}
	}

	@Override
	public String activeinactiveMaterialOutNote(ActiveInactiveEntityDto aiedto) {
		if(materialOutNoterepo.existsById(aiedto.getId())) {
			int res = materialOutNoterepo.activeinactiveMaterialOutNote(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteMaterialOutNote(MaterialOutNoteDto MaterialOutNoteDto) {
		if(materialOutNoterepo.existsById(MaterialOutNoteDto.getId())) {
			materialOutNoterepo.delete(modelMapper.map(MaterialOutNoteDto, MaterialOutNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
