package com.tva.mk.controller;

import java.util.Date;
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

import com.tva.mk.bll.AccountService;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Account;
import com.tva.mk.req.AccountReq;
import com.tva.mk.req.BaseReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/account")
public class AccountController {
	// region -- Fields --

	@Autowired
	private AccountService accountService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			String keyword = req.getKeyword();

			// Handle
			List<Account> tmp = accountService.search(id, keyword);

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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestHeader HttpHeaders header, @RequestBody AccountReq req) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			// Get data
			int id = req.getId();
			String type = req.getType();
			String text = req.getText();
			String description = req.getDescription();
			String currency = req.getCurrency();
			String bank = req.getBank();
			Float balance = req.getBalance();
			String term = req.getTerm();
			Float interestRate = req.getInterestRate();
			Date startDate = req.getStartDate();
			Float interestRateFree = req.getInterestRateFree();
			String interestPaid = req.getInterestPaid();
			String termEnded = req.getTermEnded();
			Integer accountId = req.getAccountId();
			Integer sequence = req.getSequence();

			// Convert data
			Account m = new Account();
			m.setId(id);
			m.setType(type);
			m.setText(text);
			m.setTermEnded(termEnded);
			m.setTerm(term);
			m.setInterestPaid(interestPaid);
			m.setDescription(description);
			m.setCurrency(currency);
			m.setBank(bank);
			m.setInterestRateFree(interestRateFree);
			m.setInterestRate(interestRate);
			m.setBalance(balance);
			m.setStartDate(startDate);
			m.setAccountId(accountId);
			m.setSequence(sequence);
			m.setUserId(userId);

			// Handle
			accountService.save(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@RequestHeader HttpHeaders header, @PathVariable("id") int id) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			Account acc = accountService.getById(id);

			// Set data
			res.setResult(acc);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}