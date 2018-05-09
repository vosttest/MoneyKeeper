package com.tva.mk.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.IncomeDao;
import com.tva.mk.model.Income;

@Service(value = "incomeService")
@Transactional
public class IncomeService {
	// region -- Fields --

	@Autowired
	private IncomeDao incomeDao;

	// end

	// region -- Methods --

	/**
	 * Load parent_id from Income
	 * 
	 * 
	 */
	public List<Income> loadParentIncome(int id) {
		List<Income> tmp = incomeDao.loadParentIncome(id);
		return tmp;
	}

	public List<Income> loadChildIncome(int id) {
		List<Income> tmp = incomeDao.loadChildIncome(id);
		return tmp;
	}

	// end
}