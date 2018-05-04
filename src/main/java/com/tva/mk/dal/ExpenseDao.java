package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Expense;

public interface ExpenseDao extends CrudRepository<Expense, Integer> {

}