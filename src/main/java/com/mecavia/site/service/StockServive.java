package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.StockDto;

public interface StockServive {
	String saveSock(StockDto stock);
	String closeStock(StockDto stock);
	List<StockDto> getStocks();
	StockDto getStock(StockDto stock);
}
