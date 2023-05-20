package com.mecavia.site.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String name;
	@Column(name = "product_desc")
	private String desc;
	private String img1URL;
	private String img2URL;
	private String img3URL;
	private String img4URL;
	private Status status;
	@OneToMany(targetEntity = ProductPrice.class,cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "id",name = "fk_price")
	private List<ProductPrice> pricelist;
	@ManyToMany
	@JoinTable(
			name = "product_colour",
			joinColumns = @JoinColumn(name = "productid"),
			inverseJoinColumns = @JoinColumn(name = "colourid")
	)
	private List<Colour> productcolours;
	@ManyToMany
	@JoinTable(
			name = "product_size",
			joinColumns = @JoinColumn(name = "productid"),
			inverseJoinColumns = @JoinColumn(name = "sizeid")
	)
	private List<Size> productsizes ;
}
