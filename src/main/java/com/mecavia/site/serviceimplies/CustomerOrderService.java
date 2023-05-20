package com.mecavia.site.serviceimplies;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.CustomerOrderDto;
import com.mecavia.site.dto.CustomerOrderProductDto;
import com.mecavia.site.entity.CustomerOrder;
import com.mecavia.site.entity.CustomerOrderProduct;
import com.mecavia.site.repo.CustomerOrderRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class CustomerOrderService  implements com.mecavia.site.service.CustomerOrderService{
	@Autowired
	private CustomerOrderRepo customerOrderrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public String saveCustomerOrder(CustomerOrderDto customerOrderdto) {
		if(customerOrderrepo.existsById(customerOrderdto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			CustomerOrder customerOrder = modelMapper.map(customerOrderdto, CustomerOrder.class);
			List<CustomerOrderProduct> customerOrderProducts = new ArrayList<>();
			int i=0;
			for(CustomerOrderProductDto coProductDto : customerOrderdto.getCustomerOrderProducts()) {
				CustomerOrderProduct coProduct = modelMapper.map(coProductDto, CustomerOrderProduct.class);
				coProduct.setCustomerOrder(customerOrder);
				coProduct.setCode("CO"+ String.format("%06d",Integer.parseInt(customerOrder.getCode().replaceAll("[^0-9]", "").substring(2) + ++i)));
				customerOrderProducts.add(coProduct);
			}
			customerOrder.setCustomerOrderProducts(customerOrderProducts);
			customerOrderrepo.save(customerOrder);
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<CustomerOrderDto> getCustomerOrders(){
	    List<CustomerOrder> customerOrderslist = customerOrderrepo.findAll();
	    return modelMapper.map(customerOrderslist,new TypeToken<List<CustomerOrderDto>>(){}.getType());
	}
	@Override
	public String updateCustomerOrder(CustomerOrderDto customerOrderdto) {
		if(customerOrderrepo.existsById(customerOrderdto.getId())) {
			CustomerOrder customerOrder = modelMapper.map(customerOrderdto, CustomerOrder.class);
			customerOrderrepo.save(customerOrder);
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	@Override
	public CustomerOrderDto getCustomerOrder(String customerOrderid) {
		CustomerOrder CustomerOrder =  customerOrderrepo.findByid(Integer.parseInt(customerOrderid));
		if(CustomerOrder == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(CustomerOrder,CustomerOrderDto.class);
		}
	}

	@Override
	public String activeinactiveCustomerOrder(ActiveInactiveEntityDto aiedto) {
		if(customerOrderrepo.existsById(aiedto.getId())) {
			int res = customerOrderrepo.activeinactiveCustomerOrder(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteCustomerOrder(CustomerOrderDto CustomerOrderDto) {
		if(customerOrderrepo.existsById(CustomerOrderDto.getId())) {
			customerOrderrepo.delete(modelMapper.map(CustomerOrderDto, CustomerOrder.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

}
