package com.tva.mk.controller;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.VoucherService;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.dto.VoucherDetailDto;
import com.tva.mk.dto.VoucherDto;
import com.tva.mk.model.Voucher;
import com.tva.mk.req.VoucherReq;
import com.tva.mk.req.VoucherSearchReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.MultipleRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/voucher")
public class VoucherController {
	// region -- Fields --

	@Autowired
	private VoucherService voucherService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody VoucherSearchReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			Date date = req.getDate();
			String keyword = req.getKeyword();

			// Handle
			List<VoucherDto> tmp = voucherService.getVoucher(keyword, id, date);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("count", tmp.size());
			data.put("data", tmp);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> view(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			// Handle
			VoucherDetailDto tmp = voucherService.getById(id, userId);

			// Set data
			res.setResult(tmp);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody VoucherReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			Integer id = req.getId();
			Integer accountId = req.getAccountId();
			String type = req.getType();
			Float total = req.getTotal();
			Float transferFee = req.getTransferFee();
			String description = req.getDescription();
			String payee = req.getPayee();
			String payer = req.getPayer();
			Integer toAccount = req.getToAccount();
			String category = req.getCategory();
			Date startDate = req.getStartDate();

			Voucher m = new Voucher();
			m.setId(id);
			m.setAccountId(accountId);
			m.setType(type);
			m.setTotal(total);
			m.setTransferFee(transferFee);
			m.setDescription(description);
			m.setPayee(payee);
			m.setPayer(payer);
			m.setToAccount(toAccount);
			m.setUserId(userId);
			m.setStartDate(startDate);

			voucherService.save(m, category);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			Voucher m = voucherService.getById(id);

			voucherService.delete(m, userId);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}