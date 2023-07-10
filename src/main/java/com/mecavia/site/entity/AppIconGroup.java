package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppIconGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String description;
	@OneToMany(targetEntity = AppIcon.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_appicon",referencedColumnName = "id")
	private List<AppIcon> appiconslist;
	private Status status;  
}
