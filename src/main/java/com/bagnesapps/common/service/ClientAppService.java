package com.bagnesapps.common.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.bagnesapps.common.model.ClientApp;
import com.bagnesapps.common.repository.ClientAppRepository;

@Service
@Transactional
public class ClientAppService extends BaseService<ClientApp> {
	
	private ClientAppRepository clientAppRepository;

	public ClientAppService(ClientAppRepository clientAppRepository) {
		super(clientAppRepository);
		this.clientAppRepository = clientAppRepository;
	}	
	
	public ClientApp findOneByName(String name) {
		return clientAppRepository.findOneByName(name);
	}
	
}
