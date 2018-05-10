package com.tva.mk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.ExpenseService;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Expense;
import com.tva.mk.rsp.MultipleRsp;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
	// region -- Fields --

	@Autowired
	private ExpenseService expenseService;

	// end

	// region -- Methods --

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header) {
		MultipleRsp res = new MultipleRsp();

		try {
			 PayloadDto pl = Utils.getTokenInfor(header);
			 int id = pl.getId();

			// Handle
			List<Expense> child = expenseService.getChild(id);
			List<Expense> parent = expenseService.getParrent(id);

			Map<String, Object> tmp = new LinkedHashMap<>();
			tmp.put("parent", parent);
			tmp.put("child", child);

			// Set data
			res.setResult(tmp);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}