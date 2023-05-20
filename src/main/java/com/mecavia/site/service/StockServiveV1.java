package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.StockDtoV1;

public interface StockServiveV1 {
	String saveSock(StockDtoV1 stock);
	String closeStock(StockDtoV1 stock);
	List<StockDtoV1> getStocks();
	StockDtoV1 getStock(StockDtoV1 stock);
}
