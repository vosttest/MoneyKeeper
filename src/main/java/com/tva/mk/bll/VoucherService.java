package com.tva.mk.bll;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.VoucherDao;
import com.tva.mk.dal.VoucherDetailDao;
import com.tva.mk.dto.ReportDto;
import com.tva.mk.dto.VoucherDetailDto;
import com.tva.mk.dto.VoucherDto;
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

	public List<ReportDto> getReports(int accountId, Date fromDate, Date toDate) {
		List<Object[]> t = voucherDao.getReports(accountId, fromDate, toDate);
		List<ReportDto> res = new ArrayList<>();
		for (Object[] item : t) {
			ReportDto t1 = new ReportDto();
			t1.setType(item[0].toString());
			t1.setCode(item[1].toString());
			t1.setText(item[2].toString());
			t1.setAmount(Float.parseFloat(item[3].toString()));
			t1.setStartDate((Date) item[4]);

			res.add(t1);
		}

		return res;
	}

	public List<VoucherDto> getVoucher(int userId, Date date) {
		List<Object[]> t = voucherDao.getVoucher(userId, date);
		List<VoucherDto> res = new ArrayList<>();
		for (Object[] item : t) {
			VoucherDto t1 = new VoucherDto();
			int id = Integer.parseInt(item[0].toString());
			int accountId = Integer.parseInt(item[1].toString());

			t1.setId(id);
			t1.setAccountId(accountId);
			t1.setType(item[2].toString());
			t1.setTotal(Double.parseDouble(item[3] != null ? item[3].toString() : "0"));
			t1.setDescription(item[4] != null ? item[4].toString() : "");
			t1.setPayee(item[5] != null ? item[5].toString() : "");
			t1.setPayer(item[6] != null ? item[6].toString() : "");
			t1.setToAccount(Integer.parseInt(item[7] != null ? item[7].toString() : "0"));
			t1.setUserId(Integer.parseInt(item[8].toString()));
			t1.setStartDate((Date) item[9]);

			List<Object[]> t2 = voucherDetailDao.getVouchersDetail(accountId, id);
			if (t2.size() > 0) {
				List<VoucherDetailDto> res1 = new ArrayList<>();
				for (Object[] item1 : t2) {
					VoucherDetailDto t3 = new VoucherDetailDto();
					t3.setAmount(Double.parseDouble(item1[0].toString()));
					t3.setCategoryText(item1[1].toString());
					t3.setIcon(item1[2].toString());
					t3.setAccountText(item1[3].toString());

					res1.add(t3);
				}

				t1.setVoucherDetail(res1);
			}

			res.add(t1);
		}

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