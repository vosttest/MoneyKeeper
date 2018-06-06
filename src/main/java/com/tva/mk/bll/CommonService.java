package com.tva.mk.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.CommonDao;
import com.tva.mk.model.Common;

@Service(value = "commonService")
@Transactional
public class CommonService {
	// region -- Fields --

	@Autowired
	private CommonDao commonDao;

	// end

	// region -- Methods --

	/**
	 * Get by type
	 * 
	 * @param type
	 * @return Data
	 */
	public List<Common> getBy(String type) {
		List<Common> res = commonDao.getBy(type);
		return res;
	}

	/**
	 * Get by type and parent value
	 * 
	 * @param type
	 * @param value
	 * @return Children data
	 */
	public List<Common> getBy(String type, String value) {
		List<Common> res = commonDao.getBy(type, value);
		return res;
	}

	/**
	 * Add new common if not exist, update if type & value exist. (value is unique)
	 * 
	 * @param m
	 * @return
	 */
	public String save(Common m) {
		String res = "";

		String type = m.getType();
		String value = m.getValue();
		Common m1 = commonDao.getUnique(type, value);
		if (m1 == null) {
			Integer sequence = commonDao.getNextSeq(type);
			m.setSequence(sequence == null ? 1 : sequence);

			m.setIsDeleted(false);
			m.setCreateBy(1);
			m.setCreateOn(new Date());

			commonDao.save(m);
		} else {
			m1.setModifyBy(1);
			m1.setModifyOn(new Date());

			m1.setText(m.getText());
			m1.setIsDeleted(false);

			commonDao.save(m1);
		}

		return res;
	}

	// end
}