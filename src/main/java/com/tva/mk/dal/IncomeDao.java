package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Income;

public interface IncomeDao extends CrudRepository<Income, Integer> {

}