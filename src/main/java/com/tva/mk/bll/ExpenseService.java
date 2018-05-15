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

	public List<Expense> getParent(int id) {
		List<Expense> res = expenseDao.getParent(id);
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

		if (id == null || id == 0) {
			int sequence = expenseDao.getNextSeq(userId);
			m.setSequence(sequence);

			m.setIsDeleted(false);
			m.setCreateBy(1);
			m.setCreateOn(new Date());

			int t = expenseDao.getNextSeqCode(userId);
			String t1 = String.format("EXP%02d", t + 1);
			m.setSequence(sequence);
			m.setCode(t1);

			expenseDao.save(m);
		} else {
			Expense m1 = expenseDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = expenseDao.getBy(id);
				m1.setModifyBy(userId);
				m1.setModifyOn(new Date());

				m1.setText(m.getText());
				m1.setDescription(m.getDescription());
				m1.setParentId(m.getParentId());
				m1.setIsDeleted(false);

				expenseDao.save(m1);
			}
		}

		return res;
	}

	public Expense getById(int id) {
		Expense res = expenseDao.getBy(id);
		return res;
	}

	public String delete(Expense m, int userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setModifyBy(userId);
			m.setModifyOn(new Date());

			m.setIsDeleted(true);

			expenseDao.save(m);
		}

		return res;
	}

	// end
}