package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.Users;

@Repository
public interface UsersDao extends CrudRepository<Users, Integer> {
	@Query("FROM Users")
	public List<Users> search1();
}