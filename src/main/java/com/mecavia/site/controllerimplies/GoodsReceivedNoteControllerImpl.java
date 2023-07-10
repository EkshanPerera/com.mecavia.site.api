package com.mecavia.site.controllerimplies;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecavia.site.controller.GoodsReceivedNoteController;
import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.GoodsReceivedNoteDto;
import com.mecavia.site.dto.ResponseDto;
import com.mecavia.site.serviceimplies.GoodsReceivedNoteServiceImpl;
import com.mecavia.site.util.VarList;


@RestController
@RequestMapping("/api/goodsreceivednotectrl")
@CrossOrigin(origins = "*")
public class GoodsReceivedNoteControllerImpl implements GoodsReceivedNoteController {
	@Autowired
	private GoodsReceivedNoteServiceImpl goodsReceivedNoteservice;
	
	@Autowired
	private ResponseDto responseDto;
	
	@Override
	public ResponseEntity<ResponseDto> getGoodsReceivedNotes() {
		try {
			List<GoodsReceivedNoteDto> goodsReceivedNotes =  goodsReceivedNoteservice.getGoodsReceivedNotes();
			if(goodsReceivedNotes.isEmpty()) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("no records found");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(goodsReceivedNotes);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
			responseDto.setMessage("no records found");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> updateGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNoteDto) {
		try {
			String res = goodsReceivedNoteservice.updateGoodsReceivedNote(goodsReceivedNoteDto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(goodsReceivedNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("GoodsReceivedNote not found");
				responseDto.setContent(goodsReceivedNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> getGoodsReceivedNote(String goodsReceivedNoteid) {
		try {
			GoodsReceivedNoteDto goodsReceivedNote =  goodsReceivedNoteservice.getGoodsReceivedNote(goodsReceivedNoteid);
			if(goodsReceivedNote==null) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("GoodsReceivedNote not found");
				responseDto.setContent(goodsReceivedNote);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(goodsReceivedNote);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
			responseDto.setMessage("no records found");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<ResponseDto> saveGoodsReceivedNote(GoodsReceivedNoteDto goodsReceivedNoteDto) {
		try {
			String res = goodsReceivedNoteservice.saveGoodsReceivedNote(goodsReceivedNoteDto);
			if(res.equals("00")){
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(goodsReceivedNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("06")) {
				responseDto.setCode(VarList.RSP_DUPLICATED);
				responseDto.setMessage("GoodsReceivedNote alrady exist");
				responseDto.setContent(goodsReceivedNoteDto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.NOT_ACCEPTABLE);
			}else {
				responseDto.setCode(VarList.RSP_FAIL);
				responseDto.setMessage("Error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@Override
	public ResponseEntity<ResponseDto> activeinactiveGoodsReceivedNote(ActiveInactiveEntityDto aiedto){
		try {
			String res = goodsReceivedNoteservice.activeinactiveGoodsReceivedNote(aiedto);
			if(res.equals("00")) {
				responseDto.setCode(VarList.RSP_SUCCESS);
				responseDto.setMessage("success");
				responseDto.setContent(aiedto);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.ACCEPTED);
			}else if(res.equals("01")) {
				responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
				responseDto.setMessage("GoodsReceivedNote not exists");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}else {
				responseDto.setCode(VarList.RSP_ERROR);
				responseDto.setMessage("error");
				responseDto.setContent(null);
				return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseDto.setCode(VarList.RSP_ERROR);
			responseDto.setMessage("error");
			responseDto.setContent(e.getMessage());
			return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
}
