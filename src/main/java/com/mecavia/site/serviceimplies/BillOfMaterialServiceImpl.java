package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.BOMMaterialDto;
import com.mecavia.site.dto.BillOfMaterialDto;
import com.mecavia.site.entity.BOMMaterial;
import com.mecavia.site.entity.BillOfMaterial;
import com.mecavia.site.entity.CustomerOrder;
import com.mecavia.site.repo.BillOfMaterialRepo;
import com.mecavia.site.repo.CustomerOrderRepo;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class BillOfMaterialServiceImpl  implements com.mecavia.site.service.BillOfMaterialService{
	@Autowired
	private BillOfMaterialRepo billOfMaterialrepo;
	
	@Autowired
	private CustomerOrderRepo customerOrderRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String saveBillOfMaterial(BillOfMaterialDto billOfMaterialdto) {
		if(billOfMaterialrepo.existsById(billOfMaterialdto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			BillOfMaterial billOfMaterial = modelMapper.map(billOfMaterialdto, BillOfMaterial.class);
			CustomerOrder customerOrder = customerOrderRepo.findById(billOfMaterialdto.getCustomerOrder().getId()).orElse(null);
			customerOrder.setStatus(Status.INITIATED);
			List<BOMMaterial> billOfMaterialProducts = new ArrayList<>();
			int i=0;
			for(BOMMaterialDto bomMaterialDto : billOfMaterialdto.getBomMaterials()) {
				BOMMaterial bomMaterial = modelMapper.map(bomMaterialDto, BOMMaterial.class);
				bomMaterial.setBillOfMaterial(billOfMaterial);
				bomMaterial.setCode("BOMM"+ String.format("%06d",Integer.parseInt(billOfMaterial.getCode().replaceAll("[^0-9]", "").substring(2) + ++i)));
				billOfMaterialProducts.add(bomMaterial);
			}
			billOfMaterial.setBomMaterials(billOfMaterialProducts);
			billOfMaterial.setCustomerOrder(customerOrder);
			billOfMaterialrepo.save(billOfMaterial);
			return VarList.RSP_SUCCESS;
		}		
	}
	
	@Override
	public List<BillOfMaterialDto> getBillOfMaterials(){
	    List<BillOfMaterial> billOfMaterialslist = billOfMaterialrepo.findAll();
	    return modelMapper.map(billOfMaterialslist,new TypeToken<List<BillOfMaterialDto>>(){}.getType());
	}
	@Override
	public String updateBillOfMaterial(BillOfMaterialDto billOfMaterialdto) {
		if(billOfMaterialrepo.existsById(billOfMaterialdto.getId())) {
			BillOfMaterial billOfMaterial = modelMapper.map(billOfMaterialdto, BillOfMaterial.class);
			billOfMaterialrepo.save(billOfMaterial);
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public BillOfMaterialDto getBillOfMaterial(String billOfMaterialid) {
		BillOfMaterial BillOfMaterial =  billOfMaterialrepo.findByid(Integer.parseInt(billOfMaterialid));
		if(BillOfMaterial == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(BillOfMaterial,BillOfMaterialDto.class);
		}
	}

	@Override
	public String activeinactiveBillOfMaterial(ActiveInactiveEntityDto aiedto) {
		if(billOfMaterialrepo.existsById(aiedto.getId())) {
			int res = billOfMaterialrepo.activeinactiveBillOfMaterial(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteBillOfMaterial(BillOfMaterialDto BillOfMaterialDto) {
		if(billOfMaterialrepo.existsById(BillOfMaterialDto.getId())) {
			billOfMaterialrepo.delete(modelMapper.map(BillOfMaterialDto, BillOfMaterial.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
