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

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseRequisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String prcode;
	@Column(unique = true)
	private  String pocode;
	@ManyToOne
	private Client supplierid; 
	private double totalAmount;
	private double grossAmount;
	private String quotationno;
	private String remark;
	@OneToMany(mappedBy = "purchaseRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PurchaseRequisitionMaterial> purchaseRequisitionMaterials;
	private String printeddate;
	private String prprinteddate;
	private Status status;
}