package com.tva.mk.bll;

import java.util.Date;
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

	public String save(Expense m) {
		String res = "";

		Integer id = m.getId();
		int userId = m.getUserId();
		Expense m1;
		if (id == null || id == 0) {
			m.setCreateBy(userId);
			m.setCreateOn(new Date());
			int tmp = expenseDao.getSequence(userId);
			String[] tmp1 = expenseDao.getCode(userId).get(0).split("EXP");
			String tmp2 = "EXP" + (Integer.parseInt(tmp1[1]) + 1);
			m.setSequence(tmp);
			m.setCode(tmp2);
			expenseDao.save(m);
		} else {
			m1 = expenseDao.getBy(id);
			m1.setModifyBy(userId);
			m1.setModifyOn(new Date());
			expenseDao.save(m1);
		}

		return res;
	}
	
	public Expense getById(int id) {
		Expense res = expenseDao.getBy(id);
		return res;
	}

	// end
}