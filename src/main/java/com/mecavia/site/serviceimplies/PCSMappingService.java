package com.mecavia.site.serviceimplies;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.PCSMappingDto;
import com.mecavia.site.dto.ProductVariantDto;
import com.mecavia.site.entity.Colour;
import com.mecavia.site.entity.PCSMapping;
import com.mecavia.site.entity.Product;
import com.mecavia.site.entity.Size;
import com.mecavia.site.repo.PCSMappingRepo;
import com.mecavia.site.repo.ProductRepoV1;
import com.mecavia.site.util.Status;

@Service
@Transactional
public class PCSMappingService  implements com.mecavia.site.service.PCSMappingService{
	@Autowired
	private ProductRepoV1 productrepo;
	
	@Autowired
	private PCSMappingRepo pcsmrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ColourService colourService;
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private PCSMappingDto pcsMappingDto;
	
	@Autowired
	private ExtendedProductService productService; 
	
	
	@Override
	public void addPCSMapping(int productid) {
		Product product = productrepo.findById(productid).get();
		List<Colour> colourlist = product.getProductcolours();
		List<Size> sizelist = product.getProductsizes();
		if(colourlist.isEmpty()) {
			for (Size size : sizelist) {
				if(!pcsmrepo.existsByProductidAndSizeidAndColouridAndStatus(productid,size.getId(),0,Status.ACTIVE)) {
					pcsmrepo.insertOnly (0,productid,size.getId(),Status.ACTIVE.ordinal());	
				}
				
			}
		}else if(sizelist.isEmpty()) {
			for (Colour colour : colourlist) {
				if(!pcsmrepo.existsByProductidAndSizeidAndColouridAndStatus(productid,0,colour.getId(),Status.ACTIVE)) {
					pcsmrepo.insertOnly (colour.getId(),productid,0,Status.ACTIVE.ordinal());	
				}
			}
		}else {
			for (Colour colour : colourlist) {
				for (Size size : sizelist) {
					if(!pcsmrepo.existsByProductidAndSizeidAndColouridAndStatus(productid,size.getId(),colour.getId(),Status.ACTIVE)) {
						pcsmrepo.insertOnly (colour.getId(),productid,size.getId(),Status.ACTIVE.ordinal());	
					}
				}
			}
		}
	}

	@Override
	public void removePCSMapping(ProductVariantDto pvdto) {
		if(pvdto.getSizeid()!=0 && pvdto.getColourid()!=0) {
			pcsmrepo.updatePCSM(pvdto.getColourid(),pvdto.getProductid(),pvdto.getSizeid(),Status.INACTIVE.ordinal());
		}else if(pvdto.getSizeid()!=0) {
			pcsmrepo.updateSizesPCSM(pvdto.getProductid(),pvdto.getSizeid(),Status.INACTIVE.ordinal());
		}else {
			pcsmrepo.updateColourPCSM(pvdto.getColourid(),pvdto.getProductid(),Status.INACTIVE.ordinal());
		}
	}

	@Override
	public PCSMappingDto getPCSM(PCSMappingDto pcsmDto) {
		PCSMapping pcsm = pcsmrepo.findPCSMappingByColouridAndSizeidAndProductid(pcsmDto.getColourid(),pcsmDto.getSizeid(),pcsmDto.getProductid());
		try {
			pcsMappingDto = getPCSMById(pcsm.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		modelMapper.map(pcsm, pcsMappingDto);
		return pcsMappingDto;
	}

	@Override
	public PCSMappingDto getPCSMById(int id) {
		if (pcsmrepo.existsById(id)) {
			PCSMappingDto pcsm = modelMapper.map(pcsmrepo.findById(id).get(), PCSMappingDto.class) ;
			int productid = pcsm.getProductid();
			int colourid = pcsm.getColourid();
			int sizeid = pcsm.getSizeid();
			try {
				pcsm.setShrinkedProductDto(productService.getShrinkedProduct(productid));
				pcsm.setColourDto(colourService.getColour(String.valueOf(colourid)));
				pcsm.setSizeDto(sizeService.getSize(String.valueOf(sizeid)));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			return pcsm;
		}
		else {
			throw new NullPointerException();
		}
	}
	

}
