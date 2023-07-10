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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String description;
	private Status status;
	@OneToMany(targetEntity = User.class,mappedBy = "roleid",cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<User> userslist;
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "userrole_appicon",
		    joinColumns = @JoinColumn(name = "userrole_id"),
		    inverseJoinColumns = @JoinColumn(name = "user_role_id"))
	private List<AppIcon> accIconList;
}
