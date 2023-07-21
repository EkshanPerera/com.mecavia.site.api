package com.mecavia.site.serviceimplies;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.InventoryDto;
import com.mecavia.site.entity.Inventory;
import com.mecavia.site.repo.InventoryRepo;
import com.mecavia.site.service.InventoryService;
import com.mecavia.site.util.InventoryId;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepo inventoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public String addToInventory(InventoryDto inventoryDto) {
		if(!inventoryRepo.existsById(inventoryDto.getInventoryId())) {
			inventoryRepo.save(modelMapper.map(inventoryDto, Inventory.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_DUPLICATED;
		}
	}
	
	@Override
	public String updateInventory(InventoryDto inventoryDto) {
		if(inventoryRepo.existsById(inventoryDto.getInventoryId())) {
			inventoryRepo.save(modelMapper.map(inventoryDto, Inventory.class));
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	
	public String coloseInventory(InventoryId inventoryId) {
		if(inventoryRepo.existsById(inventoryId)) {
			inventoryRepo.closeInventory(Status.CLOSED.ordinal(),inventoryId.getStatus().ordinal(),inventoryId.getStockid());
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	public String cancelInventory(InventoryId inventoryId) {
		if(inventoryRepo.existsById(inventoryId)) {
//			inventoryRepo.closeInventory(Status.CANCELED.ordinal(),inventoryId.getStatus().ordinal(),inventoryId.getStockid());
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public String removeFromInventory(int pcsmid,int status) {
		if(inventoryRepo.existsBypcsmidandsatus(pcsmid,status)==1) {
			inventoryRepo.deleteBypcsmidandstatus(pcsmid,status);
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public InventoryDto getInventory(int pcsmid,int status) {
		if (inventoryRepo.findBypcsmidandstatus(pcsmid,status)!=null) {
			return modelMapper.map(inventoryRepo.findBypcsmidandstatus(pcsmid,status), InventoryDto.class) ;
		}else {
			return null;
		}
		
	}
	
	
	public InventoryDto getInventory(InventoryId inventoryid) {
		if(inventoryRepo.existsById(inventoryid)) {
			return modelMapper.map(inventoryRepo.findById(inventoryid).get(), InventoryDto.class) ;
		}else {
			throw new NullPointerException();
		}
	}

	
}
