package com.tva.mk.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.AccountDao;
import com.tva.mk.dto.AccountDto;
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
		List<Account> res = accountDao.search(id, keyword);
		return res;
	}

	public List<AccountDto> search(String keyword, int id) {
		List<AccountDto> res = new ArrayList<>();

		List<Object[]> l = accountDao.search(keyword, id);
		for (Object[] i : l) {
			AccountDto t = new AccountDto();
			t.setId((int) i[0]);
			t.setCode((String) i[1]);
			t.setText((String) i[2]);
			t.setBalance((double) i[3]);
			t.setCurrency((String) i[4]);
			t.setRate((Double) i[5]);
			res.add(t);
		}

		return res;
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
			m.setCreateBy(userId);
			m.setCreateOn(new Date());

			m1 = accountDao.save(m);

		} else {
			m1 = accountDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m.setModifyBy(userId);
				m.setModifyOn(new Date());

				m1.setType(m.getType());
				m1.setText(m.getText());
				m1.setTermEnded(m.getTermEnded());
				m1.setTerm(m.getTerm());
				m1.setInterestPaid(m.getInterestPaid());
				m1.setDescription(m.getDescription());
				m1.setCurrency(m.getCurrency());
				m1.setBank(m.getBank());
				m1.setUserId(userId);
				m1.setSequence(m.getSequence());
				m1.setAccountId(m.getAccountId());
				m1.setInterestRateFree(m.getInterestRateFree());
				m1.setInterestRate(m.getInterestRate());
				m1.setBalance(m.getBalance());
				m1.setStartDate(m.getStartDate());

				accountDao.save(m1);
			}
		}

		return res;
	}

	public Account getById(int id) {
		Account res = accountDao.getBy(id);
		return res;
	}

	public String delete(Account m, int userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setModifyBy(userId);
			m.setModifyOn(new Date());

			m.setIsDeleted(true);

			accountDao.save(m);
		}

		return res;
	}

	// end
}