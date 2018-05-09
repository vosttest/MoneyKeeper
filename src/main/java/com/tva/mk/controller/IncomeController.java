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

import com.tva.mk.bll.IncomeService;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Income;
import com.tva.mk.rsp.MultipleRsp;

@RestController
@RequestMapping("/income")
public class IncomeController {
	// region -- Fields --

	@Autowired
	private IncomeService incomeService;

	// end

	// region -- Methods --

	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header) {
		MultipleRsp res = new MultipleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			List<Income> parent = incomeService.loadParentIncome(id);
			List<Income> child = incomeService.loadChildIncome(id);

			// Set data
			Map<String, Object> tmp2 = new LinkedHashMap<>();
			tmp2.put("parent", parent);
			tmp2.put("child", child);

			res.setResult(tmp2);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}