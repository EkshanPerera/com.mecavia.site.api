package com.mecavia.site.serviceimplies;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mecavia.site.dto.GeneralStoreDto;
import com.mecavia.site.entity.GeneralStore;
import com.mecavia.site.repo.GeneralStoreRepo;

@Service
@Transactional
public class GeneralStoreService {
	@Autowired
	private GeneralStoreRepo generalStoreRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<GeneralStoreDto> getGeneralStore () {
		List<GeneralStore> generalStoreList = generalStoreRepo.findAll();
		return modelMapper.map(generalStoreList, new TypeToken<List<GeneralStoreDto>>(){}.getType());
	}
}
