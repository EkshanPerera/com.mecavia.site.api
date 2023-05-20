package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MaterialRequisition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String enterddate;
	@ManyToOne
	private BillOfMaterial billOfMaterial;
	@OneToMany(mappedBy = "materialRequisition", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MaterialRequisitionMaterial> materialRequisitionMaterials;
	private String printeddate;
}
