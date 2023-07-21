package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.UserGroupDto;
import com.mecavia.site.entity.UserGroup;
import com.mecavia.site.repo.UserGroupRepo;
import com.mecavia.site.service.UserGroupService;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
	@Autowired
	private UserGroupRepo userGroupRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public String saveUserGroup(UserGroupDto userGroupDto) {
		if (userGroupRepo.existsById(userGroupDto.getId())) {
			return VarList.RSP_DUPLICATED;
		} else {
			userGroupRepo.save(modelMapper.map(userGroupDto, UserGroup.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateUserGroup(UserGroupDto userGroupDto) {
		if(userGroupRepo.existsById(userGroupDto.getId())){
			userGroupRepo.save(modelMapper.map(userGroupDto, UserGroup.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}	

	@Override
	public String activeinactiveUserGroup(ActiveInactiveEntityDto aiedto) {
		if(userGroupRepo.existsById(aiedto.getId())) {
			int res = userGroupRepo.activeinactiveUserGroup(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
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
	public UserGroupDto getuserGroup(int userGroupId) {
		UserGroup userGroup = userGroupRepo.findById(userGroupId).get();
		return modelMapper.map(userGroup, UserGroupDto.class);
	}

	@Override
	public List<UserGroupDto> getUserGroups() {
		List<UserGroup> userGroupList =  userGroupRepo.findAll(); 
		return modelMapper.map(userGroupList, new TypeToken<List<UserGroupDto>>(){}.getType());
	}

}
