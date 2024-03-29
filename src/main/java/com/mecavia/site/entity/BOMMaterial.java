package com.mecavia.site.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BOMMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bommaterial_id")
	private BillOfMaterial billOfMaterial;
	@ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
	private double materialCost;
	private double quantity;
}
