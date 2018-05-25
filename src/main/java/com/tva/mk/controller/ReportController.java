package com.tva.mk.controller;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.VoucherService;
import com.tva.mk.dto.ReportDto;
import com.tva.mk.req.ReportReq;
import com.tva.mk.rsp.MultipleRsp;

@RestController
@RequestMapping("/report")
public class ReportController {
	// region -- Fields --

	@Autowired
	private VoucherService voucherService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody ReportReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			int[] accountId = req.getAccountId();
			Date fromDate = req.getFromDate();
			Date toDate = req.getToDate();

			// Handle
			List<ReportDto> tmp = voucherService.getByExpense(accountId, fromDate, toDate);
			double t = tmp.stream().mapToDouble(f -> f.getAmount()).sum();

			List<ReportDto> tmp2 = voucherService.getByIncome(accountId, fromDate, toDate);
			double t2 = tmp2.stream().mapToDouble(f -> f.getAmount()).sum();

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("data", tmp);
			data.put("total", t);
			data.put("data2", tmp2);
			data.put("total2", t2);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}