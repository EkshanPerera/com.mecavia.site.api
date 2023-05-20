package com.mecavia.site.service;

import java.util.List;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ClientDto;

public interface ClientService {
	String saveClient(ClientDto clientdto);
	List<ClientDto> getClients();
	String updateClient(ClientDto clientdto);
	ClientDto getClient(String clientid);
	String activeinactiveClient(ActiveInactiveEntityDto aiedto);
}
