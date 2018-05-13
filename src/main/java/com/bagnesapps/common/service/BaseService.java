package com.bagnesapps.common.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public abstract class BaseService<T> {
	
	private CrudRepository<T, Long> repository;

	public BaseService(CrudRepository<T, Long> repository) {
		this.repository = repository;
	}
	
	public T saveOrUpdate(T model) {
		return this.repository.save(model);
	}
	
	public void delete(T model) {
		this.repository.delete(model);
	}
	
	public T findById(long id) {
		return this.repository.findOne(id);
	}
	
	public List<T> findByIds(List<Long> ids){
		return (List<T>) this.repository.findAll(ids);
	}
	
	public List<T> findAll(){
		return (List<T>) this.repository.findAll();
	}
}
