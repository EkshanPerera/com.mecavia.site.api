package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialRequisitionDto;
import com.mecavia.site.dto.MaterialRequisitionMaterialDto;
import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.entity.Material;
import com.mecavia.site.entity.MaterialRequisition;
import com.mecavia.site.entity.MaterialRequisitionMaterial;
import com.mecavia.site.repo.GeneralStoreRepo;
import com.mecavia.site.repo.MaterialRequisitionRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class MaterialRequisitionServiceImpl  implements com.mecavia.site.service.MaterialRequisitionService{
	@Autowired
	private MaterialRequisitionRepo materialRequisitionrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GeneralStoreRepo generalStoreRepo;
	
	@Override
	public String saveMaterialRequisition(MaterialRequisitionDto materialRequisitiondto) {
		if(materialRequisitionrepo.existsById(materialRequisitiondto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			MaterialRequisition materialRequisition = modelMapper.map(materialRequisitiondto, MaterialRequisition.class);
			List<MaterialRequisitionMaterial> materialRequisitionMaterials = new ArrayList<>();
			
			for(MaterialRequisitionMaterialDto mrMaterialDto : materialRequisitiondto.getMaterialRequisitionMaterials()) {
			    MaterialRequisitionMaterial mrMaterial = modelMapper.map(mrMaterialDto, MaterialRequisitionMaterial.class);
			    mrMaterial.setMaterialRequisition(materialRequisition);
			    materialRequisitionMaterials.add(mrMaterial);
			    Material material = mrMaterialDto.getBommaterial().getMaterial();
			    double requestedCount = mrMaterialDto.getRequestedCount();
			    GeneralStore newGeneralStore =  generalStoreRepo.findByMaterial(material);
		    	newGeneralStore.setRequestedItemcount(newGeneralStore.getRequestedItemcount() + requestedCount);
		    	
		    	generalStoreRepo.save(newGeneralStore);
			}
			
			materialRequisition.setMaterialRequisitionMaterials(materialRequisitionMaterials);
			materialRequisitionrepo.save(materialRequisition);
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<MaterialRequisitionDto> getMaterialRequisitions(){
	    List<MaterialRequisition> materialRequisitionslist = materialRequisitionrepo.findAll();
	    return modelMapper.map(materialRequisitionslist,new TypeToken<List<MaterialRequisitionDto>>(){}.getType());
	}
	@Override
	public String updateMaterialRequisition(MaterialRequisitionDto materialRequisitiondto) {
		if(materialRequisitionrepo.existsById(materialRequisitiondto.getId())) {
			materialRequisitionrepo.save(modelMapper.map(materialRequisitiondto, MaterialRequisition.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public MaterialRequisitionDto getMaterialRequisition(String materialRequisitionid) {
		MaterialRequisition MaterialRequisition =  materialRequisitionrepo.findByid(Integer.parseInt(materialRequisitionid));
		if(MaterialRequisition == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(MaterialRequisition,MaterialRequisitionDto.class);
		}
	}

	@Override
	public String activeinactiveMaterialRequisition(ActiveInactiveEntityDto aiedto) {
		if(materialRequisitionrepo.existsById(aiedto.getId())) {
			int res = materialRequisitionrepo.activeinactiveMaterialRequisition(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteMaterialRequisition(MaterialRequisitionDto MaterialRequisitionDto) {
		if(materialRequisitionrepo.existsById(MaterialRequisitionDto.getId())) {
			materialRequisitionrepo.delete(modelMapper.map(MaterialRequisitionDto, MaterialRequisition.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
