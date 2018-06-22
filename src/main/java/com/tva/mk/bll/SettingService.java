package com.tva.mk.bll;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.SettingDao;
import com.tva.mk.model.Setting;

@Service(value = "settingService")
@Transactional
public class SettingService {
	// region -- Fields --

	@Autowired
	private SettingDao settingDao;

	@PersistenceContext
	private EntityManager entityManager;

	// end

	// region -- Methods --

	/**
	 * Get by user id and code
	 * 
	 * @param id
	 *            User id
	 * @param code
	 * @return
	 */
	public Setting getBy(int id, String code) {
		Setting res = settingDao.getBy(id, code);
		return res;
	}

	/**
	 * Search by user id
	 * 
	 * @param id
	 *            User id
	 * @return
	 */
	public List<Setting> search(int id) {
		List<Setting> res = settingDao.search(id);
		return res;
	}

	/**
	 * Update currency of all default account after sign up, just run one time.
	 * 
	 * @param userId
	 */
	public void setAccountDefault(int userId) {
		settingDao.setAccountDefault(userId);
	}

	public String save(Setting m) {
		String res = "";

		Integer id = m.getId();

		Setting m1;
		if (id == null || id == 0) {
			m.setCreateBy(1);
			m.setCreateOn(new Date());

			m1 = settingDao.save(m);

		} else {
			m1 = settingDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1.setValue(m.getValue());
				m1.setDescription(m.getDescription());
				m1.setStatus(m.getStatus());
				m1.setModifyBy(1);
				m1.setModifyOn(new Date());

				m1 = settingDao.save(m1);
			}
		}

		return res;
	}

	/**
	 * Reset authentication
	 * 
	 * @param id
	 *            User id
	 */
	public void resetAuth(int id) {
		try {
			entityManager.createNamedQuery("updateSetting", Setting.class).setParameter(1, id).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// end
}