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
public class GoodsReceivedNoteMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String ordercode;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "goods_received_note")
    private GoodsReceivedNote goodsReceivedNote;
	@ManyToOne
    @JoinColumn(name = "prmaterial_id")
    private PurchaseRequisitionMaterial prmaterial;
	private double arrivedCount;
}
