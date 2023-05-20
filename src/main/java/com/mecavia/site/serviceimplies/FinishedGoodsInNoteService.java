package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.FinishedGoodsInNoteDto;
import com.mecavia.site.dto.FinishedGoodsInNoteProductDto;
import com.mecavia.site.entity.FinishedGoodsInNote;
import com.mecavia.site.entity.FinishedGoodsInNoteProduct;
import com.mecavia.site.entity.InventoryItem;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.Stock;
import com.mecavia.site.repo.FinishedGoodsInNoteRepo;
import com.mecavia.site.repo.InventoryItemRepo;
import com.mecavia.site.repo.StockRepo;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class FinishedGoodsInNoteService  implements com.mecavia.site.service.FinishedGoodsInNoteService{
	@Autowired
	private FinishedGoodsInNoteRepo finishedGoodsInNoterepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StockRepo stockRepo;
	
	@Autowired
	private InventoryItemRepo inventoryItemRepo;
	
	@Override
	public String saveFinishedGoodsInNote(FinishedGoodsInNoteDto finishedGoodsInNotedto) {
		if(finishedGoodsInNoterepo.existsById(finishedGoodsInNotedto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			FinishedGoodsInNote finishedGoodsInNote = modelMapper.map(finishedGoodsInNotedto, FinishedGoodsInNote.class);
			List<FinishedGoodsInNoteProduct> finishedGoodsInNoteProducts = new ArrayList<>();
			List<InventoryItem> inventoryItems = new ArrayList<>();
			for(FinishedGoodsInNoteProductDto fginProductDto : finishedGoodsInNotedto.getFinishedGoodsInNoteProducts()) {
			    FinishedGoodsInNoteProduct fginProduct = modelMapper.map(fginProductDto, FinishedGoodsInNoteProduct.class);
			    fginProduct.setFinishedGoodsInNote(finishedGoodsInNote);
			    finishedGoodsInNoteProducts.add(fginProduct);
			    Product product = fginProductDto.getCoproduct().getProduct();
			    double finishedCount = fginProductDto.getFinishedCount();
			    Stock newStock =  stockRepo.findByProduct(product);
		        newStock.setItemcount(newStock.getItemcount() + finishedCount);
		        stockRepo.save(newStock);
		        for(int i = 0;i < finishedCount;i++) {
		        	InventoryItem newInventoryItem = new InventoryItem();
		        	newInventoryItem.setCode("1"+ String.format("%013d",Integer.parseInt(finishedGoodsInNote.getCode().replaceAll("[^0-9]", "") + Integer.parseInt(fginProduct.getCode().replaceAll("[^0-9]", "")  + i))));
			        newInventoryItem.setCustomerOrder(finishedGoodsInNote.getCustomerOrder());
			        newInventoryItem.setFgiBulck(fginProduct);
			        newInventoryItem.setStatus(Status.ACTIVE);
			        inventoryItems.add(newInventoryItem);
		        };
			}
			finishedGoodsInNote.setFinishedGoodsInNoteProducts(finishedGoodsInNoteProducts);
			finishedGoodsInNoterepo.save(finishedGoodsInNote);
			inventoryItemRepo.saveAll(inventoryItems);
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<FinishedGoodsInNoteDto> getFinishedGoodsInNotes(){
	    List<FinishedGoodsInNote> finishedGoodsInNoteslist = finishedGoodsInNoterepo.findAll();
	    return modelMapper.map(finishedGoodsInNoteslist,new TypeToken<List<FinishedGoodsInNoteDto>>(){}.getType());
	}
	@Override
	public String updateFinishedGoodsInNote(FinishedGoodsInNoteDto finishedGoodsInNotedto) {
		if(finishedGoodsInNoterepo.existsById(finishedGoodsInNotedto.getId())) {
			finishedGoodsInNoterepo.save(modelMapper.map(finishedGoodsInNotedto, FinishedGoodsInNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public FinishedGoodsInNoteDto getFinishedGoodsInNote(String finishedGoodsInNoteid) {
		FinishedGoodsInNote FinishedGoodsInNote =  finishedGoodsInNoterepo.findByid(Integer.parseInt(finishedGoodsInNoteid));
		if(FinishedGoodsInNote == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(FinishedGoodsInNote,FinishedGoodsInNoteDto.class);
		}
	}

	@Override
	public String activeinactiveFinishedGoodsInNote(ActiveInactiveEntityDto aiedto) {
		if(finishedGoodsInNoterepo.existsById(aiedto.getId())) {
			int res = finishedGoodsInNoterepo.activeinactiveFinishedGoodsInNote(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteFinishedGoodsInNote(FinishedGoodsInNoteDto FinishedGoodsInNoteDto) {
		if(finishedGoodsInNoterepo.existsById(FinishedGoodsInNoteDto.getId())) {
			finishedGoodsInNoterepo.delete(modelMapper.map(FinishedGoodsInNoteDto, FinishedGoodsInNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
