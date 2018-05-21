package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.TokenAuthentication;;

public interface TokenAuthenticationDao extends CrudRepository<TokenAuthentication, Integer> {
	
}