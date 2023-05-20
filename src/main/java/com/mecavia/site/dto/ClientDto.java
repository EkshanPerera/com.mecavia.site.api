package com.mecavia.site.dto;

import java.util.List;

import com.mecavia.site.entity.ClientAddress;
import com.mecavia.site.entity.ClientContact;
import com.mecavia.site.util.Role;
import com.mecavia.site.util.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {
	private int id;
	private String code;
	private String firstname;
	private String middlename;
	private String lastname;
	private String email;
	private Role businessRole;
	private Status status;
	private List<ClientAddress> addresses;
	private List<ClientContact> contactNumbers;
}
