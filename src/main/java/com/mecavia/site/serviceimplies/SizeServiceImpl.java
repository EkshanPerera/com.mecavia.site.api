package com.mecavia.site.serviceimplies;

import java.util.List;



import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.SizeDto;
import com.mecavia.site.entity.Size;
import com.mecavia.site.repo.SizeRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class SizeServiceImpl implements com.mecavia.site.service.SizeService {
	@Autowired
	private SizeRepo sizerepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String saveSize(SizeDto size) {
		if(sizerepo.existsById(size.getId())) {
			return VarList.RSP_DUPLICATED;
		}else{
			sizerepo.save(modelMapper.map(size, Size.class));
			return VarList.RSP_SUCCESS;
		}
	}

	@Override
	public String updateSize(SizeDto size) {
		if(sizerepo.existsById(size.getId())) {
			sizerepo.save(modelMapper.map(sizerepo, Size.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

	@Override
	public List<SizeDto> getSizes() {
		List<Size> sizes = sizerepo.findAll();
		return modelMapper.map(sizes, new TypeToken<List<SizeDto>>(){}.getType());
	}

	@Override
	public SizeDto getSize(String sizeid) {
		Size size = sizerepo.findByid(Integer.parseInt(sizeid));
		return modelMapper.map(size, SizeDto.class);
	}

	@Override
	public String activeinactiveSize(ActiveInactiveEntityDto aiedto) {
		if(sizerepo.existsById(aiedto.getId())) {
			int res = sizerepo.activeinactiveColour(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}

}
