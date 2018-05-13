package com.bagnesapps.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.bagnesapps.common.model.ClientApp;

public interface ClientAppRepository extends CrudRepository<ClientApp, Long> {

	ClientApp findOneByName(String name);

}
