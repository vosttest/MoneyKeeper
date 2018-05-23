package com.tva.mk.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.VoucherDao;
import com.tva.mk.dal.VoucherDetailDao;
import com.tva.mk.model.Voucher;
import com.tva.mk.model.VoucherDetail;

@Service(value = "voucherService")
@Transactional
public class VoucherService {
	// region -- Fields --

	@Autowired
	private VoucherDao voucherDao;

	@Autowired
	private VoucherDetailDao voucherDetailDao;

	// end

	// region -- Methods --

	public Voucher getById(int id) {
		Voucher res = voucherDao.getBy(id);
		return res;
	}

	public List<Object[]> getByExpense(int[] accountId, Date fromDate, Date toDate) {
		List<Object[]> res = voucherDao.getExpense(accountId, fromDate, toDate);
		return res;
	}

	public List<Object[]> getByIncome(int[] accountId, Date fromDate, Date toDate) {
		List<Object[]> res = voucherDao.getIncome(accountId, fromDate, toDate);
		return res;
	}

	public String save(Voucher m, String category) {
		String res = "";

		Integer id = m.getId();
		int userId = m.getUserId();

		Voucher m1;
		if (id == null || id == 0) {

			m.setCreateBy(userId);
			m.setCreateOn(new Date());

			m1 = voucherDao.save(m);

			VoucherDetail m2 = new VoucherDetail();
			m2.setVoucherId(m1.getId());
			m2.setAmount(m.getTotal());
			m2.setCategory(category);
			voucherDetailDao.save(m2);
		} else {
			m1 = voucherDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m.setModifyBy(userId);
				m.setModifyOn(new Date());

				m1.setType(m.getType());
				m1.setTotal(m.getTotal());
				m1.setAccountId(m.getAccountId());
				m1.setDescription(m.getDescription());
				m1.setPayee(m.getPayee());
				m1.setUserId(userId);
				m1.setStartDate(m.getStartDate());

				voucherDao.save(m1);

				VoucherDetail m2 = new VoucherDetail();
				m2.setVoucherId(m1.getId());
				m2.setAmount(m.getTotal());
				m2.setCategory(category);

				voucherDetailDao.save(m2);
			}
		}

		return res;
	}

	public String delete(Voucher m, int userId) {
		String res = "";

		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setModifyBy(userId);
			m.setModifyOn(new Date());

			m.setIsDeleted(true);

			voucherDao.save(m);
		}

		return res;
	}

	// end
}