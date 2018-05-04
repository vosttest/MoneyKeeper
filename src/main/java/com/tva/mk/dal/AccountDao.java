package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Account;

public interface AccountDao extends CrudRepository<Account, Integer> {

}