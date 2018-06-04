package com.tva.mk.bll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public List<VoucherDto> getVoucher(String keyword, int userId, Date date) {
		List<Object[]> t = voucherDao.getVoucher(keyword, userId, date);
		List<VoucherDto> res = new ArrayList<>();
		VoucherDto t1 = new VoucherDto();
		List<VoucherDetailDto> lstVoucher = new ArrayList<>();
		int count = 0;
		for (Object[] item : t) {
			SimpleDateFormat fo = new SimpleDateFormat("yyyy-MM-dd");
			Date t3 = new Date();
			try {
				t3 = fo.parse(item[0].toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int a = date.compareTo(t3);
			if (a > 0) {
				date = (Date) item[0];
				if (t1.getStartDate() == null) {
					t1.setStartDate((Date) item[0]);
					res.add(t1);
				}
				count++;
			} else {
				t1 = new VoucherDto();
				lstVoucher = new ArrayList<>();
				t1.setStartDate((Date) item[0]);
				date = (Date) item[0];
				count = 0;
			}

			VoucherDetailDto t2 = new VoucherDetailDto();
			t2.setVoucherId(Integer.parseInt(item[1].toString()));
			t2.setAccountId(Integer.parseInt(item[2].toString()));
			String type = item[3].toString();
			t2.setType(type);
			t2.setTotal(Double.parseDouble(item[4] != null ? item[4].toString() : "0.0"));
			t2.setDescription(item[5] != null ? item[5].toString() : "");
			t2.setPayee(item[6] != null ? item[6].toString() : "");
			t2.setPayer(item[7] != null ? item[7].toString() : "");
			t2.setToAccount(Integer.parseInt(item[8] != null ? item[8].toString() : "0"));
			t2.setUserId(Integer.parseInt(item[9].toString()));
			t2.setAmount(Double.parseDouble(item[10].toString()));
			t2.setCategoryText((item[11].toString()));
			t2.setIcon(item[12].toString());
			t2.setAccountText(item[13].toString());
			t2.setId(Integer.parseInt(item[14].toString()));

			lstVoucher.add(t2);
			t1.setVoucherDetail(lstVoucher);

			double totalExpense = t1.getVoucherDetail().stream().filter(f -> f.getType().equals("Expense"))
					.mapToDouble(VoucherDetailDto::getAmount).sum();
			double totalIncome = t1.getVoucherDetail().stream().filter(f -> f.getType().equals("Income"))
					.mapToDouble(VoucherDetailDto::getAmount).sum();

			t1.setTotalExpense(totalExpense);
			t1.setTotalIncome(totalIncome);

			if (count == 0) {
				res.add(t1);
			}
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