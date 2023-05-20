package com.mecavia.site.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customerorder_product" )
public class CustomerOrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	@Column(unique = true)
	private String code;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "customer_order_id")
    private CustomerOrder customerOrder;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
	private double quantity;
	private double unitrate;
}
