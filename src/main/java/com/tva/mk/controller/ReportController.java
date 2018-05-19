package com.tva.mk.controller;

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

			// Handle
			List<Object[]> tmp = voucherService.getByExpense(accountId);

			// Set data
			Map<String, Object> t = new LinkedHashMap<>();
			t.put("data", tmp);
			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}