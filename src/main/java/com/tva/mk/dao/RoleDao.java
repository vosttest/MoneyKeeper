package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {
	@Query("FROM Role")
	public List<Role> search1();
}