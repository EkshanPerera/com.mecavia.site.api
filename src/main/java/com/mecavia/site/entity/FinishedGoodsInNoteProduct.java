package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FinishedGoodsInNoteProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	//code of customer order products
	private String cordercode;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "finished_goods_in_note")
    private FinishedGoodsInNote finishedGoodsInNote;
	@ManyToOne
    @JoinColumn(name = "coproduct_id")
    private CustomerOrderProduct coproduct;
	private double finishedCount;
	@OneToMany(targetEntity = InventoryItem.class,mappedBy = "fgiBulck",cascade = CascadeType.PERSIST)
	private List<InventoryItem> inventoryItems;
}
