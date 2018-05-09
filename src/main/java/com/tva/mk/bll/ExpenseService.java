package com.tva.mk.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.ExpenseDao;
import com.tva.mk.model.Expense;

@Service(value = "expenseService")
@Transactional
public class ExpenseService {
	// region -- Fields --

	@Autowired
	private ExpenseDao expenseDao;

	// end

	// region -- Methods --

	public List<Expense> getParrent(int id) {
		List<Expense> res = expenseDao.getParrent(id);
		return res;
	}

	public List<Expense> getChild(int id) {
		List<Expense> res = expenseDao.getChild(id);
		return res;
	}

	// end
}