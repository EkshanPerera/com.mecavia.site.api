package com.mecavia.site.serviceimplies;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.InventoryDtoV1;
import com.mecavia.site.dto.StockDtoV1;
import com.mecavia.site.entity.StockV1;
import com.mecavia.site.repo.StockRepoV1;
import com.mecavia.site.service.StockServiveV1;
import com.mecavia.site.util.InventoryId;
import com.mecavia.site.util.Status;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class StockServiceV1 implements StockServiveV1 {
	
	@Autowired
	private StockRepoV1 stockRepoV1;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PCSMappingServiceImpl pcsmService;
	
	@Autowired
	private InventoryId inventoryId;
	
	@Autowired
	private InventoryDtoV1 inventoryDtoV1;
	
	@Autowired
	private InventoryServiceV1 inventoryServiceV1;
	
	@Autowired
	private VarList varList;
	

	
	
	@Override
	public String saveSock(StockDtoV1 stock) {
		stock.setPcsmid(pcsmService.getPCSM(stock.getPcsmdto()).getId());
		stock.setOpeningdate(varList.getSYSDATE());
		if(stockRepoV1.existsById(stock.getId()) || stockRepoV1.existsByPcsmidAndStatus(stock.getPcsmid(), stock.getStatus())) {
			return VarList.RSP_DUPLICATED;
		}else {
			try {
				inventoryDtoV1 = inventoryServiceV1.getInventory(stock.getPcsmid(),Status.CLOSED.ordinal());
				if(inventoryDtoV1!=null) {
					inventoryDtoV1.setItemcount(stock.getOpeningcount()+inventoryDtoV1.getItemcount());
					stock.setOpeningcount(inventoryDtoV1.getItemcount());
					inventoryServiceV1.removeFromInventory(stock.getPcsmid(),Status.CLOSED.ordinal());
				}else {
					inventoryDtoV1 = new InventoryDtoV1();
					inventoryDtoV1.setItemcount(stock.getOpeningcount());
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
				inventoryDtoV1.setItemcount(stock.getOpeningcount());
			}
			
			stock = modelMapper.map(stockRepoV1.saveAndFlush(modelMapper.map(stock, StockV1.class)),StockDtoV1.class);
			
			inventoryId.setStockid(stock.getId());
			inventoryId.setStatus(stock.getStatus());
			inventoryDtoV1.setInventoryId(inventoryId);
			inventoryDtoV1.setPrice(stock.getOpeningprice());
			inventoryDtoV1.setPcsmid(stock.getPcsmid());
			
			try {
				inventoryServiceV1.addToInventory(inventoryDtoV1);
				return VarList.RSP_SUCCESS;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return VarList.RSP_ERROR;
			}
			
		}
	}
	
	
	

	@Override
	public String closeStock(StockDtoV1 stock) {
		if(!stockRepoV1.existsByIdAndStatus(stock.getId(),stock.getStatus())) {
			inventoryId.setStockid(stock.getId());
			inventoryId.setStatus(Status.OPENED);
			try {
				inventoryDtoV1 = inventoryServiceV1.getInventory(inventoryId);
				stock = modelMapper.map(stockRepoV1.findById(stock.getId()).get(),StockDtoV1.class);
				stock.setColosingcount(inventoryDtoV1.getItemcount());
				stock.setClosingprice(inventoryDtoV1.getPrice());
				stock.setColosingdate(varList.getSYSDATE());
				stock.setStatus(stock.getStatus());
				stockRepoV1.save(modelMapper.map(stock, StockV1.class));
				
				try {
					inventoryServiceV1.coloseInventory(inventoryId);
					return VarList.RSP_SUCCESS;
				} catch (Exception e) {
					System.out.println(e.getMessage());
					return VarList.RSP_ERROR;
				}
				
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return VarList.RSP_ERROR;
			}
		}else {
			return VarList.RSP_DUPLICATED;
		}
	}

	


	@Override
	public List<StockDtoV1> getStocks() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public StockDtoV1 getStock(StockDtoV1 stockDtoV1) {
		if(stockRepoV1.existsByPcsmidAndStatus(stockDtoV1.getPcsmid(), stockDtoV1.getStatus())){
			return modelMapper.map(stockRepoV1.findStockByPcsmidAndStatus(stockDtoV1.getPcsmid(), stockDtoV1.getStatus()),StockDtoV1.class);
		}else {
			return null;
		}
	}

}
