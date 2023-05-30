package com.mecavia.site.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecavia.site.util.Role;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String firstname;
	private String middlename; 
	private String lastname;
	private String email;
	@Enumerated(EnumType.STRING)
	private Role businessRole;
	private Status status;
	@OneToMany(targetEntity = ClientAddress.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address",referencedColumnName = "id")
	private List<ClientAddress> addresses;
	@OneToMany(targetEntity = ClientContact.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_tpno",referencedColumnName = "id")
	private List<ClientContact> contactNumbers;
	@OneToMany(targetEntity = PurchaseRequisition.class,mappedBy = "supplierid",cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<PurchaseRequisition> prlist;
	@OneToMany(targetEntity = CustomerOrder.class,mappedBy = "customerid",cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<PurchaseRequisition> orderlist;
}
