package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.FinishedGoodsOutNoteDto;
import com.mecavia.site.dto.FinishedGoodsOutNoteProductDto;
import com.mecavia.site.entity.Stock;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.FinishedGoodsOutNote;
import com.mecavia.site.entity.FinishedGoodsOutNoteProduct;
import com.mecavia.site.repo.StockRepo;
import com.mecavia.site.repo.FinishedGoodsOutNoteRepo;
import com.mecavia.site.repo.CustomerOrderRepo;
import com.mecavia.site.service.FinishedGoodsOutNoteService;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class FinishedGoodsOutNoteServiceImpl  implements FinishedGoodsOutNoteService{
	@Autowired
	private FinishedGoodsOutNoteRepo finishedGoodsOutNoterepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private StockRepo generalStoreRepo;
	
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	
	@Override
	public String saveFinishedGoodsOutNote(FinishedGoodsOutNoteDto finishedGoodsOutNotedto) throws Exception {
		if(finishedGoodsOutNoterepo.existsById(finishedGoodsOutNotedto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			FinishedGoodsOutNote finishedGoodsOutNote = modelMapper.map(finishedGoodsOutNotedto, FinishedGoodsOutNote.class);
			List<FinishedGoodsOutNoteProduct> finishedGoodsOutNoteProducts = new ArrayList<>();
			int i=0;
			for(FinishedGoodsOutNoteProductDto fgonProductDto : finishedGoodsOutNotedto.getFinishedGoodsOutNoteProducts()) {
			    FinishedGoodsOutNoteProduct fgonProduct = modelMapper.map(fgonProductDto, FinishedGoodsOutNoteProduct.class);
			    fgonProduct.setCode("MONM"+ String.format("%06d",Integer.parseInt(finishedGoodsOutNote.getCode().replaceAll("[^0-9]", "").substring(2) + ++i)));
			    fgonProduct.setFinishedGoodsOutNote(finishedGoodsOutNote);
			    finishedGoodsOutNoteProducts.add(fgonProduct);
			    Product product = fgonProductDto.getProduct();
			    double releasedCount = fgonProductDto.getReleasedCount();
			    Stock newStock =  generalStoreRepo.findByProduct(product);
		    	newStock.setReleasedItemcount(newStock.getReleasedItemcount() + releasedCount);
		        generalStoreRepo.save(newStock);
			}
			finishedGoodsOutNote.setFinishedGoodsOutNoteProducts(finishedGoodsOutNoteProducts);
			finishedGoodsOutNoterepo.save(finishedGoodsOutNote);
			customerOrderRepo.activeinactiveCustomerOrder(finishedGoodsOutNotedto.getCustomerOrder().getId(), finishedGoodsOutNotedto.getCustomerOrder().getCode(), Status.CLOSED.ordinal());
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<FinishedGoodsOutNoteDto> getFinishedGoodsOutNotes(){
	    List<FinishedGoodsOutNote> finishedGoodsOutNoteslist = finishedGoodsOutNoterepo.findAll();
	    return modelMapper.map(finishedGoodsOutNoteslist,new TypeToken<List<FinishedGoodsOutNoteDto>>(){}.getType());
	}
	@Override
	public String updateFinishedGoodsOutNote(FinishedGoodsOutNoteDto finishedGoodsOutNotedto) {
		if(finishedGoodsOutNoterepo.existsById(finishedGoodsOutNotedto.getId())) {
			finishedGoodsOutNoterepo.save(modelMapper.map(finishedGoodsOutNotedto, FinishedGoodsOutNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public FinishedGoodsOutNoteDto getFinishedGoodsOutNote(String finishedGoodsOutNoteid) {
		FinishedGoodsOutNote FinishedGoodsOutNote =  finishedGoodsOutNoterepo.findByid(Integer.parseInt(finishedGoodsOutNoteid));
		if(FinishedGoodsOutNote == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(FinishedGoodsOutNote,FinishedGoodsOutNoteDto.class);
		}
	}

	@Override
	public String activeinactiveFinishedGoodsOutNote(ActiveInactiveEntityDto aiedto) {
		if(finishedGoodsOutNoterepo.existsById(aiedto.getId())) {
			int res = finishedGoodsOutNoterepo.activeinactiveFinishedGoodsOutNote(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteFinishedGoodsOutNote(FinishedGoodsOutNoteDto FinishedGoodsOutNoteDto) {
		if(finishedGoodsOutNoterepo.existsById(FinishedGoodsOutNoteDto.getId())) {
			finishedGoodsOutNoterepo.delete(modelMapper.map(FinishedGoodsOutNoteDto, FinishedGoodsOutNote.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
