package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.Function;

@Repository
public interface FunctionDao extends CrudRepository<Function, Integer> {
	@Query("FROM Function")
	public List<Function> search1();
}