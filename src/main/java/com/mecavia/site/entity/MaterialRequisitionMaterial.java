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
public class MaterialRequisitionMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String ordercode;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "goods_received_note")
    private MaterialRequisition materialRequisition;
	@ManyToOne
    @JoinColumn(name = "prmaterial_id")
    private BOMMaterial bommaterial;
	private double arrivedCount;
}
