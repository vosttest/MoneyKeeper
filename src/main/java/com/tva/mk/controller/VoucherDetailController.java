package com.tva.mk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.dto.VoucherDetailDto;
import com.tva.mk.req.VoucherDetailReq;
import com.tva.mk.rsp.SingleRsp;
import com.tva.mk.service.VoucherDetailService;

@RestController
@RequestMapping("/VoucherDetail")
public class VoucherDetailController {
	// region -- Fields --

	@Autowired
	private VoucherDetailService voucherDetailService;

	// end

	// region -- Methods --

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> getVoucherByCreateBy(@RequestHeader HttpHeaders header,
			@RequestBody VoucherDetailReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			Integer idMaster = req.getIdMaster();

			// Handle
			List<VoucherDetailDto> dtos = voucherDetailService.viewVoucherDetailByIdMaster(idMaster);

			// Set data
			res.setResult(dtos);
		} catch (Exception ex) {
			res.setCallstatus("error");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}