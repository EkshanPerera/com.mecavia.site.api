package com.mecavia.site.controllerimplies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.dto.GeneralStoreDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.GeneralStoreService;
import com.mecavia.site.util.VarList;

@RestController
@RequestMapping("/api/generalstorectrl")
@CrossOrigin(origins = "*")
public class GeneralStoreController {
	@Autowired
	private GeneralStoreService generalStoreService;
	
	@Autowired
	private ResponseDto responseDto;
	
	@GetMapping("/getgeneralstorelist")
	public ResponseEntity<ResponseDto> getGeneralStoreList() {
		try {
			List<GeneralStoreDto> generalStoreList = generalStoreService.getGeneralStore();
			if(!generalStoreList.isEmpty()) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(generalStoreList);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("Intrnal server error");
			responseDto.setContent(null);
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
