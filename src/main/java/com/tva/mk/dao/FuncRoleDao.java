package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.FuncRole;

@Repository
public interface FuncRoleDao extends CrudRepository<FuncRole, Integer> {
	@Query("FROM FuncRole")
	public List<FuncRole> search1();
}