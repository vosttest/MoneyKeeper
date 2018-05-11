package com.tva.mk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.IncomeService;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Income;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.MultipleRsp;
import com.tva.mk.rsp.SingleRsp;

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
			List<Income> child = incomeService.getChild(id);
			List<Income> parent = incomeService.getParent(id);

			// Set data
			Map<String, Object> t = new LinkedHashMap<>();
			t.put("parent", parent);
			t.put("child", child);
			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody Income req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			String text = req.getText();
			String description = req.getDescription();
			int parentId = req.getParentId();

			Income m = new Income();
			m.setText(text);
			m.setDescription(description);
			m.setUserId(id);
			if (parentId != 0) {
				m.setParentId(parentId);
			}

			incomeService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			Income exp = incomeService.getById(id);

			// Set data
			res.setResult(exp);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}