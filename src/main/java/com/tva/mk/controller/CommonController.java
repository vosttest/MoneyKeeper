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

import com.tva.mk.bll.CommonService;
import com.tva.mk.model.Common;
import com.tva.mk.req.BaseReq;
import com.tva.mk.rsp.MultipleRsp;

@RestController
@RequestMapping("/common")
public class CommonController {
	// region -- Fields --

	@Autowired
	private CommonService commonService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody BaseReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			String keyword = req.getKeyword();

			// Handle
			List<Common> tmp = commonService.getByType(keyword);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", tmp.size());
			data.put("data", tmp);

			// Set data
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}