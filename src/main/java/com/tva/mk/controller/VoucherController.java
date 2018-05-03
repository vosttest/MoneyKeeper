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

import com.tva.mk.model.Voucher;
import com.tva.mk.req.VoucherReq;
import com.tva.mk.rsp.SingleRsp;
import com.tva.mk.service.VoucherService;

@RestController
@RequestMapping("/Voucher")
public class VoucherController {
	// region -- Fields --

	@Autowired
	private VoucherService voucherService;

	// end

	// region -- Methods --

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> getVoucherByCreateBy(@RequestHeader HttpHeaders header, @RequestBody VoucherReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String type = req.getType();
			Integer createBy = req.getCreateBy();

			// Handle
			List<Voucher> dtos = voucherService.getVoucherByType(type, createBy);

			// Set data
			res.setResult(dtos);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ResponseEntity<?> modify(@RequestHeader HttpHeaders header, @RequestBody VoucherReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String type = req.getType();
			Integer createBy = req.getCreateBy();

			// Handle
			List<Voucher> dtos = voucherService.getVoucherByType(type, createBy);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", dtos.size());
			data.put("data", dtos);
			res.setResult(data);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}