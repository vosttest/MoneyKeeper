package com.tva.mk.controller;

import static com.tva.mk.common.Constants.HEADER_STRING;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.AccountService;
import com.tva.mk.common.Utils;
import com.tva.mk.model.Account;
import com.tva.mk.req.BaseReq;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/account")
public class AccountController {
	// region -- Fields --

	@Autowired
	private AccountService ser;

	// end

	// region -- Methods --

	@PostMapping("")
	public ResponseEntity<?> searchAccount(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		SingleRsp rsp = new SingleRsp();

		// Get data
		String keyWord = req.getKeyword();
		String token = header.get(HEADER_STRING).get(0);
		int userId = Utils.getUserIdFromToken(token);

		// Handle
		List<Account> res = ser.searchAccountBelongToUserId(userId, keyWord);

		// Set data
		rsp.setResult(res);

		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	// end
}