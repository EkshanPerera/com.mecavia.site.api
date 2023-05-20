package com.mecavia.site.serviceimplies;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.MaterialDto;
import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.entity.Material;
import com.mecavia.site.repo.GeneralStoreRepo;
import com.mecavia.site.repo.MaterialRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class MaterialService implements com.mecavia.site.service.MaterialService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MaterialRepo materialRepo;

	@Autowired
	private GeneralStoreRepo generalStoreRepo;
	@Override
	public String saveMaterial(MaterialDto materialDto) {
		if(materialRepo.existsById(materialDto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			Material material = modelMapper.map(materialDto, Material.class);
			material = materialRepo.saveAndFlush(material);
	        GeneralStore generalStore = new GeneralStore();
	        generalStore.setMaterialid(material);
	        generalStore.setStatus(material.getStatus());
	        generalStoreRepo.save(generalStore);
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateMaterial(MaterialDto materialDto) {
		if(materialRepo.existsById(materialDto.getId())) {
			materialRepo.save(modelMapper.map(materialDto, Material.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public String activeInactiveMaterial(ActiveInactiveEntityDto activeInactiveEntityDto) {
		if(materialRepo.existsById(activeInactiveEntityDto.getId())) {
			int res = materialRepo.activeinactiveMaterial(activeInactiveEntityDto.getId(), activeInactiveEntityDto.getCode(), activeInactiveEntityDto.getStatus().ordinal());
			res = generalStoreRepo.activeinactiveGenaralStore(activeInactiveEntityDto.getId(), activeInactiveEntityDto.getStatus().ordinal()); 
			if (res > 0) {
			    return VarList.RSP_SUCCESS;
			} else {
			    return VarList.RSP_ERROR;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public MaterialDto getMaterial(int id) {
		Material material = materialRepo.findById(id).get();
		return modelMapper.map(material, MaterialDto.class);
	}

	@Override
	public List<MaterialDto> getMaterials() {
		List<Material> materials = materialRepo.findAll();
		return modelMapper.map(materials, new TypeToken<List<MaterialDto>>(){}.getType());
	}

}
