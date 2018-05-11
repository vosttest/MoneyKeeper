package com.tva.mk.bll;

import java.util.Date;
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

	public List<Income> getParent(int id) {
		List<Income> res = incomeDao.getParent(id);
		return res;
	}

	public List<Income> getChild(int id) {
		List<Income> res = incomeDao.getChild(id);
		return res;
	}

	public String save(Income m) {
		String res = "";

		Integer id = m.getId();
		int userId = m.getUserId();

		Income m1;
		if (id == null || id == 0) {
			int sequence = incomeDao.getNextSeq(userId);
			m.setSequence(sequence);

			m.setIsDeleted(false);
			m.setCreateBy(1);
			m.setCreateOn(new Date());

			int t = incomeDao.getNextSeqCode(userId);
			String t1 = String.format("INC%02d", t + 1);
			m.setSequence(sequence);
			m.setCode(t1);

			m1 = incomeDao.save(m);

		} else {
			m1 = incomeDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1 = incomeDao.getBy(id);
				m1.setModifyBy(userId);
				m1.setModifyOn(new Date());

				incomeDao.save(m1);
			}
		}

		return res;
	}

	public Income getById(int id) {
		Income res = incomeDao.getBy(id);
		return res;
	}

	// end
}