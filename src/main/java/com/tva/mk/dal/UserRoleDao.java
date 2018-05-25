package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, Integer> {

}