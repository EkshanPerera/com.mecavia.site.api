package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
public class MaterialOutNote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	private String enterddate;
	@ManyToOne
	private MaterialRequisition materialRequisition;
	@OneToMany(mappedBy = "materialOutNote", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MaterialOutNoteMaterial> materialOutNoteMaterials;
	private String printeddate;
	private String remark;
	private Status status;
	@ManyToOne
	private User enteredUser;
}
