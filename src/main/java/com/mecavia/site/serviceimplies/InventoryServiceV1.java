package com.mecavia.site.serviceimplies;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.InventoryDtoV1;
import com.mecavia.site.entity.InventoryV1;
import com.mecavia.site.repo.InventoryRepoV1;
import com.mecavia.site.util.InventoryId;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class InventoryServiceV1 implements com.mecavia.site.service.InventoryServiceV1 {
	
	@Autowired
	private InventoryRepoV1 inventoryRepoV1;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public String addToInventory(InventoryDtoV1 inventoryDtoV1) {
		if(!inventoryRepoV1.existsById(inventoryDtoV1.getInventoryId())) {
			inventoryRepoV1.save(modelMapper.map(inventoryDtoV1, InventoryV1.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_DUPLICATED;
		}
	}
	
	@Override
	public String updateInventory(InventoryDtoV1 inventoryDtoV1) {
		if(inventoryRepoV1.existsById(inventoryDtoV1.getInventoryId())) {
			inventoryRepoV1.save(modelMapper.map(inventoryDtoV1, InventoryV1.class));
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	
	public String coloseInventory(InventoryId inventoryId) {
		if(inventoryRepoV1.existsById(inventoryId)) {
			inventoryRepoV1.closeInventory(Status.CLOSED.ordinal(),inventoryId.getStatus().ordinal(),inventoryId.getStockid());
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	public String cancelInventory(InventoryId inventoryId) {
		if(inventoryRepoV1.existsById(inventoryId)) {
//			inventoryRepoV1.closeInventory(Status.CANCELED.ordinal(),inventoryId.getStatus().ordinal(),inventoryId.getStockid());
			return VarList.RSP_SUCCESS;
		}else {
			throw new NullPointerException();
		}
	}
	
	@Override
	public String removeFromInventory(int pcsmid,int status) {
		if(inventoryRepoV1.existsBypcsmidandsatus(pcsmid,status)==1) {
			inventoryRepoV1.deleteBypcsmidandstatus(pcsmid,status);
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public InventoryDtoV1 getInventory(int pcsmid,int status) {
		if (inventoryRepoV1.findBypcsmidandstatus(pcsmid,status)!=null) {
			return modelMapper.map(inventoryRepoV1.findBypcsmidandstatus(pcsmid,status), InventoryDtoV1.class) ;
		}else {
			return null;
		}
		
	}
	
	
	public InventoryDtoV1 getInventory(InventoryId inventoryid) {
		if(inventoryRepoV1.existsById(inventoryid)) {
			return modelMapper.map(inventoryRepoV1.findById(inventoryid).get(), InventoryDtoV1.class) ;
		}else {
			throw new NullPointerException();
		}
	}

	
}
