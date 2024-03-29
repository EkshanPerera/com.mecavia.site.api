package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BillOfMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String code;
	@OneToOne(targetEntity = CustomerOrder.class,cascade = CascadeType.PERSIST)
	private CustomerOrder customerOrder;
	@OneToMany(targetEntity = BOMMaterial.class,mappedBy = "billOfMaterial",cascade = CascadeType.ALL)
	private List<BOMMaterial> bomMaterials;
	private double totalcost;
	private Status status;
	private String enteredDate;
	@ManyToOne
	private User enteredUser;
}
