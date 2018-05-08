package com.tva.mk.bll;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.AccountDao;
import com.tva.mk.model.Account;

@Service(value = "accountService")
@Transactional
public class AccountService {
	// region -- Fields --

	@Autowired
	private AccountDao accountDao;

	@PersistenceContext
	private EntityManager entityManager;

	// end

	// region -- Methods --

	/**
	 * Search by user id with keyword
	 * 
	 * @param id
	 *            User id
	 * @param keyword
	 * @return
	 */
	public List<Account> search(int id, String keyword) {
		List<Account> tmp = accountDao.search(id, keyword);
		return tmp;
	}

	public String save(Account m) {
		String res = "";

		Integer id = m.getId();
		int userId = m.getUserId();

		Account m1;
		if (id == null || id == 0) {
			int sequence = accountDao.getNextSeq(userId);
			m.setSequence(sequence);

			m.setIsDeleted(false);
			m.setCreateBy(1);
			m.setCreateOn(new Date());

			m1 = accountDao.save(m);

		} else {
			m1 = accountDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m.setModifyBy(1);
				m.setModifyOn(new Date());

				m1 = entityManager.merge(m);
			}
		}

		return res;
	}

	public String delete(int id) {
		String res = "";

		Account m = accountDao.getBy(id);
		if (m != null) {
			m.setIsDeleted(true);
			accountDao.save(m);
		}

		return res;
	}

	// end
}