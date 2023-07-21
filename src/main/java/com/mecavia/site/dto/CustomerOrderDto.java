package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.BillOfMaterial;
import com.mecavia.site.entity.Client;
import com.mecavia.site.entity.InventoryItem;
import com.mecavia.site.entity.Invoice;
import com.mecavia.site.entity.User;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrderDto {
	private int id;
	private String code;
	private  String jobID;
	private  String jobNumber;
	private Client customerid; 
	private double totalAmount;
	private double grossAmount;
	private String remark;
	private List<CustomerOrderProductDto> customerOrderProducts;
	private String printeddate;
	private Status status;
	private List<InventoryItem> inventoryItems;
	private BillOfMaterial billOfMaterial;
	private List<Invoice> invoices;
	private String enteredDate;
	private String acceptedDate;
	private User enteredUser;
	private User acceptedUser; 
}