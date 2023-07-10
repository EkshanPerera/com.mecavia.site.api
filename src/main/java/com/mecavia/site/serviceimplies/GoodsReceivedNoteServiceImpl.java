package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.GoodsReceivedNoteDto;
import com.mecavia.site.dto.GoodsReceivedNoteMaterialDto;
import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.entity.GoodsReceivedNote;
import com.mecavia.site.entity.GoodsReceivedNoteMaterial;
import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.PurchaseRequisitionMaterial;
import com.mecavia.site.repo.GeneralStoreRepo;
import com.mecavia.site.repo.GoodsReceivedNoteRepo;
import com.mecavia.site.repo.PurchaseRequisitionMaterialRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class GoodsReceivedNoteServiceImpl  implements com.mecavia.site.service.GoodsReceivedNoteService{
	@Autowired
	private GoodsReceivedNoteRepo goodsReceivedNoterepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GeneralStoreRepo generalStoreRepo;
	
	@Autowired
	private PurchaseRequisitionMaterialRepo purchaseRequisitionMaterialRepo;
	
	
	@Override
	public String saveGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNotedto) {
		if(goodsReceivedNoterepo.existsById(goodsReceivedNotedto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			GoodsReceivedNote goodsReceivedNote = modelMapper.map(goodsReceivedNotedto, GoodsReceivedNote.class);
			List<GoodsReceivedNoteMaterial> goodsReceivedNoteMaterials = new ArrayList<>();
			PurchaseRequisitionMaterial prm = new PurchaseRequisitionMaterial();
			int i=0;
			double totArrivedCount = 0;
			for(GoodsReceivedNoteMaterialDto grnMaterialDto : goodsReceivedNotedto.getGoodsReceivedNoteMaterials()) {
			    GoodsReceivedNoteMaterial grnMaterial = modelMapper.map(grnMaterialDto, GoodsReceivedNoteMaterial.class);
			    grnMaterial.setCode("GRNM"+ String.format("%06d",Integer.parseInt(goodsReceivedNote.getCode().replaceAll("[^0-9]", "").substring(2) + ++i)));
			    grnMaterial.setGoodsReceivedNote(goodsReceivedNote);
			    goodsReceivedNoteMaterials.add(grnMaterial);
			    Material material = grnMaterialDto.getPrmaterial().getMaterial();
			    double arrivedCount = grnMaterialDto.getArrivedCount();
			    GeneralStore newGeneralStore =  generalStoreRepo.findByMaterial(material);
		        newGeneralStore.setItemcount(newGeneralStore.getItemcount() + arrivedCount);
		        modelMapper.map(purchaseRequisitionMaterialRepo.findBycode(grnMaterialDto.getOrdercode()),prm) ;
		        totArrivedCount = prm.getTotArrivedCount();
		        totArrivedCount += grnMaterialDto.getArrivedCount();
		        generalStoreRepo.save(newGeneralStore);
		        purchaseRequisitionMaterialRepo.setTotArrivedCount(prm.getId(),totArrivedCount);
		        
			}
			goodsReceivedNote.setGoodsReceivedNoteMaterials(goodsReceivedNoteMaterials);
			goodsReceivedNoterepo.save(goodsReceivedNote);
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<GoodsReceivedNoteDto> getGoodsReceivedNotes(){
	    List<GoodsReceivedNote> goodsReceivedNoteslist = goodsReceivedNoterepo.findAll();
	    return modelMapper.map(goodsReceivedNoteslist,new TypeToken<List<GoodsReceivedNoteDto>>(){}.getType());
	}
	@Override
	public String updateGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNotedto) {
		if(goodsReceivedNoterepo.existsById(goodsReceivedNotedto.getId())) {
			goodsReceivedNoterepo.save(modelMapper.map(goodsReceivedNotedto, GoodsReceivedNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public GoodsReceivedNoteDto getGoodsReceivedNote(String goodsReceivedNoteid) {
		GoodsReceivedNote GoodsReceivedNote =  goodsReceivedNoterepo.findByid(Integer.parseInt(goodsReceivedNoteid));
		if(GoodsReceivedNote == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(GoodsReceivedNote,GoodsReceivedNoteDto.class);
		}
	}

	@Override
	public String activeinactiveGoodsReceivedNote(ActiveInactiveEntityDto aiedto) {
		if(goodsReceivedNoterepo.existsById(aiedto.getId())) {
			int res = goodsReceivedNoterepo.activeinactiveGoodsReceivedNote(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteGoodsReceivedNote(GoodsReceivedNoteDto GoodsReceivedNoteDto) {
		if(goodsReceivedNoterepo.existsById(GoodsReceivedNoteDto.getId())) {
			goodsReceivedNoterepo.delete(modelMapper.map(GoodsReceivedNoteDto, GoodsReceivedNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
