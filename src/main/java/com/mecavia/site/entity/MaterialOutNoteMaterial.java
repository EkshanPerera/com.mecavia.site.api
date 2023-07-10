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
public class MaterialOutNoteMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String code;
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "materialoutnote")
	private MaterialOutNote materialOutNote;
	@ManyToOne
    @JoinColumn(name = "material")
    private Material material;
	private double releasedCount;
}
