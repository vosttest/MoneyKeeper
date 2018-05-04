package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {

}