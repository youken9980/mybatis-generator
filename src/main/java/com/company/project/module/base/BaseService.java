package com.company.project.module.base;

public interface BaseService<T, ID> {

	T get(ID id) throws Exception;

	T save(T entity) throws Exception;

	T update(T entity) throws Exception;

	ID delete(ID id) throws Exception;
}
