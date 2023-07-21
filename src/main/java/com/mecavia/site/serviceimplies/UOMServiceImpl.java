package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UOMDto;
import com.mecavia.site.entity.UOM;
import com.mecavia.site.repo.UOMRepo;
import com.mecavia.site.service.UOMService;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class UOMServiceImpl implements UOMService {
	@Autowired
	private UOMRepo uOMRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public String saveUOM(UOMDto uOMDto) {
		if (uOMRepo.existsById(uOMDto.getId())) {
			return VarList.RSP_DUPLICATED;
		} else {
			uOMRepo.save(modelMapper.map(uOMDto, UOM.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateUOM(UOMDto uOMDto) {
		if(uOMRepo.existsById(uOMDto.getId())){
			uOMRepo.save(modelMapper.map(uOMDto, UOM.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}	

	@Override
	public String activeinactiveUOM(ActiveInactiveEntityDto aiedto) {
		if(uOMRepo.existsById(aiedto.getId())) {
			int res = uOMRepo.activeinactiveUOM(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public UOMDto getuOM(int uOMId) {
		UOM uOM = uOMRepo.findById(uOMId).get();
		return modelMapper.map(uOM, UOMDto.class);
	}

	@Override
	public List<UOMDto> getUOMs() {
		List<UOM> uOMList =  uOMRepo.findAll(); 
		return modelMapper.map(uOMList, new TypeToken<List<UOMDto>>(){}.getType());
	}

}
