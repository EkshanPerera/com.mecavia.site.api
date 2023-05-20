package com.mecavia.site.entity;

import java.util.Collection;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mecavia.site.util.Role;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String code;
	private String firstname;
	private String middlename; 
	private String lastname;
	@Column(unique = true)
	private String email;
	@JsonIgnore
	private String password;
	private boolean firstLogin;  
	@Enumerated(EnumType.STRING)
	private Role businessRole;
	private Status status;
	@OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address",referencedColumnName = "id")
	private List<Address> addresses;
	@OneToMany(targetEntity = Contact.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_tpno",referencedColumnName = "id")
	private List<Contact> contactNumbers;
	@ManyToOne
	private UserGroup userGroupid;
	@ManyToOne
	private UserRole roleid;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(businessRole.name()));
	}
	@Override
	public String getUsername() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		if(status == Status.INACTIVE) {
			return false;
		}else {
			return true;
		}
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
