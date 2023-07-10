package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.AppIconGroupDto;
import com.mecavia.site.entity.AppIconGroup;
import com.mecavia.site.repo.AppIconGroupRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class AppIconGroupServiceImpl implements com.mecavia.site.service.AppIconGroupService {
	@Autowired
	private AppIconGroupRepo appIconGroupRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public String saveAppIconGroup(AppIconGroupDto appIconGroupDto) {
		if (appIconGroupRepo.existsById(appIconGroupDto.getId())) {
			return VarList.RSP_DUPLICATED;
		} else {
			appIconGroupRepo.save(modelMapper.map(appIconGroupDto, AppIconGroup.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateAppIconGroup(AppIconGroupDto appIconGroupDto) {
		if(appIconGroupRepo.existsById(appIconGroupDto.getId())){
//			AppIconGroup appIconGroup = modelMapper.map(appIconGroupDto, AppIconGroup.class);
//			List<AppIcon> appIcons = new ArrayList<>();
//			for(AppIcon appIcon : appIconGroupDto.getAppiconslist()) {
//				appIcon.setAppIconGroup(appIconGroup);
//			    appIcons.add(appIcon);
//			}
//			appIconGroup.setAppiconslist(appIcons);
			appIconGroupRepo.save(modelMapper.map(appIconGroupDto, AppIconGroup.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}	

	@Override
	public String activeinactiveAppIconGroup(ActiveInactiveEntityDto aiedto) {
		if(appIconGroupRepo.existsById(aiedto.getId())) {
			int res = appIconGroupRepo.activeinactiveAppIconGroup(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
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
	public AppIconGroupDto getappIconGroup(int appIconGroupId) {
		AppIconGroup appIconGroup = appIconGroupRepo.findById(appIconGroupId).get();
		return modelMapper.map(appIconGroup, AppIconGroupDto.class);
	}

	@Override
	public List<AppIconGroupDto> getAppIconGroups() {
		List<AppIconGroup> appIconGroupList =  appIconGroupRepo.findAll(); 
		return modelMapper.map(appIconGroupList, new TypeToken<List<AppIconGroupDto>>(){}.getType());
	}

}
