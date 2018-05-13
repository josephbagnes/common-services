package com.bagnesapps.common.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bagnesapps.common.model.ClientApp;
import com.bagnesapps.common.model.CustomException;

@Service
public class SecurityService {

	@Autowired
	private ClientAppService clientAppService;
	
	public ClientApp isAllowed(String name, String key) throws CustomException {
		ClientApp app = clientAppService.findOneByName(name);
		if(null != app && StringUtils.equals(app.getKey(), key)) {
			return app;
		}
		throw new CustomException("UNAUTHORIZED");
	}
	
}
