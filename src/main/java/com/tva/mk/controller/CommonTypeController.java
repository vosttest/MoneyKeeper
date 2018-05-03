package com.tva.mk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.model.CommonType;
import com.tva.mk.req.BaseReq;
import com.tva.mk.req.CommonTypeReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.MultipleRsp;
import com.tva.mk.rsp.SingleRsp;
import com.tva.mk.service.CommonTypeService;

@RestController
@RequestMapping("/CommonType")
public class CommonTypeController {
	// region -- Fields --

	@Autowired
	private CommonTypeService commonTypeService;

	// end

	@RequestMapping(value = "/search1", method = RequestMethod.GET)
	public ResponseEntity<?> search1(@RequestHeader HttpHeaders header) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<CommonType> tmp = commonTypeService.search1();

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", tmp.size());
			data.put("data", tmp);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/search2", method = RequestMethod.GET)
	public ResponseEntity<?> search2(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			List<CommonType> tmp = commonTypeService.search2();

			// Set data
			res.setResult(tmp);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/search3", method = RequestMethod.POST)
	public ResponseEntity<?> search3(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String keyword = req.getKeyword();

			// Handle
			List<CommonType> tmp = commonTypeService.search3(keyword);

			// Set data
			res.setResult(tmp);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody CommonTypeReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Get data
			int id = req.getId();
			String typeName = req.getTypeName();
			boolean isDeleted = req.getIsDeleted();

			// Convert data request -> model
			CommonType m = new CommonType();
			m.setId(id);
			m.setTypeName(typeName);
			m.setDeleted(isDeleted);

			// Handle
			String tmp = commonTypeService.save(m);

			// Set data
			res.setMessage(tmp);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}