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

	/**
	 * Load parent_id from Income
	 * 
	 * 
	 */
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

			m.setCreateBy(userId);
			m.setCreateOn(new Date());
			int tmp = incomeDao.getSequence(userId);
			String[] tmp1 = incomeDao.getCode(userId).get(0).split("EXP");
			String tmp2 = "EXP" + (Integer.parseInt(tmp1[1]) + 1);
			m.setSequence(tmp);
			m.setCode(tmp2);
			incomeDao.save(m);

		} else {

			m1 = incomeDao.getBy(id);
			m1.setModifyBy(userId);
			m1.setModifyOn(new Date());
			incomeDao.save(m1);
		}
		return res;
	}

	// end
}