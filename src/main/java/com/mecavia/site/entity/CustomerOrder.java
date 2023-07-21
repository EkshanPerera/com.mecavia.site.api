package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	//should generate 
	@Column(unique = true)
	private  String jobID;
	private  String jobNumber;
	@ManyToOne
	private Client customerid; 
	private double totalAmount;
	private double grossAmount;
	private String remark;
	@OneToMany(mappedBy = "customerOrder", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CustomerOrderProduct> customerOrderProducts;
	private String printeddate;
	private Status status;
	@JsonIgnore
	@OneToMany(targetEntity = InventoryItem.class, mappedBy = "customerOrder", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<InventoryItem> inventoryItems;
	@JsonIgnore
	@OneToOne(targetEntity = BillOfMaterial.class,mappedBy = "customerOrder",cascade = CascadeType.PERSIST)
	private BillOfMaterial billOfMaterial;
	private String enteredDate;
	private String acceptedDate;
	@ManyToOne
	private User enteredUser;
	@ManyToOne
	private User acceptedUser; 
	@OneToMany(targetEntity = Invoice.class,mappedBy = "customerOrder",cascade = CascadeType.ALL)
	private List<Invoice> invoices;
}
