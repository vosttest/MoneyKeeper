package com.tva.mk.service.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.VoucherDao;
import com.tva.mk.model.Voucher;
import com.tva.mk.service.VoucherService;

@Service(value = "voucherService")
@Transactional
public class VoucherImpl implements VoucherService {
	// region -- Fields --

	@Autowired
	private VoucherDao dao;

	@PersistenceContext
	private EntityManager entityManager;

	// end

	// region -- Methods --

	@Override
	public List<Voucher> getVoucherByCreateBy(Integer createBy) {
		List<Voucher> res = dao.getVoucherByCreateBy(createBy);
		return res;
	}

	@Override
	public List<Voucher> getVoucherByType(String type, Integer createBy) {
		List<Voucher> res = dao.getVoucherByType(type, createBy);
		return res;
	}

	@Override
	public List<Voucher> getVoucherByTypeForAdmin(Integer createBy) {
		List<Voucher> res = dao.getVoucherByTypeForAdmin(createBy);
		return res;
	}

	@Override
	public String modify(Voucher v) {
		if (v.getId() == null) {
			Voucher res = dao.save(v);
			return res.getId().toString();
		} else {
			Voucher res = dao.getVoucherById(v.getId());
			if (res != null) {
				res = entityManager.merge(v);
				return res.getId().toString();
			}
			return null;
		}
	}

	@Override
	public String delete(int id) {
		Voucher v = dao.getVoucherById(id);
		if (v != null) {
			v.setIsDeleted(true);
			v = entityManager.merge(v);
			return v.getId().toString();
		}
		return null;
	}

	// end
}