package com.tva.mk.bll;

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

	// end
}