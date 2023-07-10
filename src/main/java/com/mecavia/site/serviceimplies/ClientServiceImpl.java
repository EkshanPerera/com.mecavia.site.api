package com.mecavia.site.serviceimplies;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mecavia.site.dto.ActiveInactiveEntityDto;
import com.mecavia.site.dto.ClientDto;
import com.mecavia.site.entity.Client;
import com.mecavia.site.repo.ClientRepo;
import com.mecavia.site.util.VarList;

@Service
@Transactional
public class ClientServiceImpl  implements com.mecavia.site.service.ClientService{
	@Autowired
	private ClientRepo clientrepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String saveClient(ClientDto clientdto) {
		if(clientrepo.existsById(clientdto.getId())) {
			return VarList.RSP_DUPLICATED;
		}else {
			clientrepo.save(modelMapper.map(clientdto, Client.class));
			return VarList.RSP_SUCCESS;
		}		
	}
	@Override
	public List<ClientDto> getClients(){
		List<Client> clientslist  = clientrepo.findAll();
		return modelMapper.map(clientslist,new TypeToken<List<ClientDto>>(){}.getType());
	}
	@Override
	public String updateClient(ClientDto clientdto) {
		if(clientrepo.existsById(clientdto.getId())) {
			clientrepo.save(modelMapper.map(clientdto, Client.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}
	@Override
	public ClientDto getClient(String clientid) {
		Client Client =  clientrepo.findByid(Integer.parseInt(clientid));
		if(Client == null) {
			throw new NullPointerException();
		}else {
			return modelMapper.map(Client,ClientDto.class);
		}
	}

	@Override
	public String activeinactiveClient(ActiveInactiveEntityDto aiedto) {
		if(clientrepo.existsById(aiedto.getId())) {
			int res = clientrepo.activeinactiveClient(aiedto.getId(),aiedto.getCode(),aiedto.getStatus().ordinal());
			if(res!=1) {
				return VarList.RSP_NO_DATA_FOUND;
			}else {
				return VarList.RSP_SUCCESS;
			}
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
	}
	
	public String deleteClient(ClientDto ClientDto) {
		if(clientrepo.existsById(ClientDto.getId())) {
			clientrepo.delete(modelMapper.map(ClientDto, Client.class));
			return VarList.RSP_SUCCESS;
		}else {
			return VarList.RSP_NO_DATA_FOUND;
		}
		
	}

	

	
	
}
