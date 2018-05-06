package com.tva.mk.bll;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.AccountDao;
import com.tva.mk.model.Account;

@Service
@Transactional
public class AccountService {
	// region -- Fields --

	@Autowired
	private AccountDao dao;

	@PersistenceContext
	private EntityManager entityManager;

	// end

	// region -- Methods --

	public List<Account> searchAccountBelongToUserId(int userId, String keyWord) {
		List<Account> tmp = dao.searchAccountBelongToUserId(userId, keyWord);
		return tmp;
	}

	public String save(Account newA) {
		Account tmp;
		if (newA.getId() == null) {
			int tmp2 = dao.getSequenceByUserId(newA.getUserId());
			newA.setSequence(tmp2);
			tmp = dao.save(newA);
		} else {
			tmp = dao.getAccountById(newA.getId());
			if (tmp == null) {
				return null;
			}
			tmp = entityManager.merge(newA);
		}
		return tmp.getId().toString();
	}

	public String delete(int id) {
		Account tmp = dao.getAccountById(id);
		if (tmp != null) {
			tmp.setIsDeleted(true);
			tmp = entityManager.merge(tmp);
			return tmp.getId().toString();
		}
		return null;
	}

	// end
}