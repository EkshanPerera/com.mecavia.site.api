package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.CustomerOrderDto;

public interface CustomerOrderService {
	String saveCustomerOrder(CustomerOrderDto customerOrderdto);
	List<CustomerOrderDto> getCustomerOrders();
	String updateCustomerOrder(CustomerOrderDto customerOrderdto);
	CustomerOrderDto getCustomerOrder(String customerOrderid);
	String activeinactiveCustomerOrder(ActiveInactiveEntityDto aiedto);
}
