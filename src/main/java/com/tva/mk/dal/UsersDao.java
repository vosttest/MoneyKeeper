package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Users;

public interface UsersDao extends CrudRepository<Users, Integer> {

}