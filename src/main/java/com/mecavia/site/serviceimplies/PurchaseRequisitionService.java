package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.PurchaseRequisitionDto;
import com.mecavia.site.dto.PurchaseRequisitionMaterialDto;
import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.PurchaseRequisition;
import com.mecavia.site.entity.PurchaseRequisitionMaterial;
import com.mecavia.site.repo.MaterialRepo;
import com.mecavia.site.repo.PurchaseRequisitionRepo;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class PurchaseRequisitionService  implements com.mecavia.site.service.PurchaseRequisitionService{
	@Autowired
	private PurchaseRequisitionRepo purchaseRequisitionrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaterialRepo materialRepo;
	
	@Override
	public String savePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitiondto) {
		if(purchaseRequisitionrepo.existsById(purchaseRequisitiondto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			PurchaseRequisition purchaseRequisition = modelMapper.map(purchaseRequisitiondto, PurchaseRequisition.class);
			List<PurchaseRequisitionMaterial> purchaseRequisitionMaterials = new ArrayList<>();
			int i=0;
			for(PurchaseRequisitionMaterialDto prMaterialDto : purchaseRequisitiondto.getPurchaseRequisitionMaterials()) {
				PurchaseRequisitionMaterial prMaterial = modelMapper.map(prMaterialDto, PurchaseRequisitionMaterial.class);
				prMaterial.setPurchaseRequisition(purchaseRequisition);
				prMaterial.setCode("RM"+ String.format("%06d",Integer.parseInt(purchaseRequisition.getPrcode().replaceAll("[^0-9]", "").substring(2) + ++i)));
				purchaseRequisitionMaterials.add(prMaterial);
			}
			purchaseRequisition.setPurchaseRequisitionMaterials(purchaseRequisitionMaterials);
			purchaseRequisitionrepo.save(purchaseRequisition);
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<PurchaseRequisitionDto> getPurchaseRequisitions(){
	    List<PurchaseRequisition> purchaseRequisitionslist = purchaseRequisitionrepo.findAll();
	    return modelMapper.map(purchaseRequisitionslist,new TypeToken<List<PurchaseRequisitionDto>>(){}.getType());
	}
	@Override
	public String updatePurchaseRequisition(PurchaseRequisitionDto purchaseRequisitiondto) {
		if(purchaseRequisitionrepo.existsById(purchaseRequisitiondto.getId())) {
			if(purchaseRequisitiondto.getStatus() == Status.APPROVED) {
				PurchaseRequisition purchaseRequisition = modelMapper.map(purchaseRequisitiondto, PurchaseRequisition.class);
				List<Material> materials = new ArrayList<>();
				for(PurchaseRequisitionMaterialDto prMaterialDto : purchaseRequisitiondto.getPurchaseRequisitionMaterials()) {
					Material prMaterial = modelMapper.map(prMaterialDto.getMaterial(), Material.class);
					prMaterial.setPrice(prMaterialDto.getUnitrate());
					materials.add(prMaterial);
				}
				materialRepo.saveAll(materials);
				purchaseRequisitionrepo.save(purchaseRequisition);
				return VarList.RSP_SUCCESS;
			}else {
				PurchaseRequisition purchaseRequisition = modelMapper.map(purchaseRequisitiondto, PurchaseRequisition.class);
				purchaseRequisitionrepo.save(purchaseRequisition);
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public PurchaseRequisitionDto getPurchaseRequisition(String purchaseRequisitionid) {
		PurchaseRequisition PurchaseRequisition =  purchaseRequisitionrepo.findByid(Integer.parseInt(purchaseRequisitionid));
		if(PurchaseRequisition == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(PurchaseRequisition,PurchaseRequisitionDto.class);
		}
	}

	@Override
	public String activeinactivePurchaseRequisition(ActiveInactiveEntityDto aiedto) {
		if(purchaseRequisitionrepo.existsById(aiedto.getId())) {
			int res = purchaseRequisitionrepo.activeinactivePurchaseRequisition(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deletePurchaseRequisition(PurchaseRequisitionDto PurchaseRequisitionDto) {
		if(purchaseRequisitionrepo.existsById(PurchaseRequisitionDto.getId())) {
			purchaseRequisitionrepo.delete(modelMapper.map(PurchaseRequisitionDto, PurchaseRequisition.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
