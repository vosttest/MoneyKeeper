package com.tva.mk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.CommonService;
import com.tva.mk.bll.SettingService;
import com.tva.mk.common.Const;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Common;
import com.tva.mk.model.Setting;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/setting")
public class SettingController {
	// region -- Fields --

	@Autowired
	private SettingService settingService;

	@Autowired
	private CommonService commonService;

	// end

	// region -- Methods --

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			List<Setting> tmp = settingService.search(id);

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
	public ResponseEntity<?> save(@RequestBody Setting req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int userId = pl.getId();

			// Get data
			int id = req.getId();
			String code = req.getCode();
			String value = req.getValue();
			String description = req.getDescription();
			String status = req.getStatus();
			String text = req.getText();

			// Convert data
			Setting m = new Setting();
			if (id == 0) {
				m.setCode(code);
				m.setText(text);
			}
			m.setId(id);
			m.setValue(value);
			m.setDescription(description);
			m.setStatus(status);
			m.setUserId(userId);

			// Handle
			String tmp = settingService.save(m);

			if (!tmp.isEmpty()) {
				res.setError("Save setting error!");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/exrate")
	public ResponseEntity<?> exrate() {
		BaseRsp res = new BaseRsp();

		try {
			String url = Const.Setting.EXRATE_URL;
			Map<String, Object> t = Utils.readXML(url);

			for (Entry<String, Object> e : t.entrySet()) {
				Common m = new Common();
				m.setType("ExchangeRate");
				m.setValue(e.getKey());
				m.setText(e.getValue().toString());
				commonService.save(m);
			}
		} catch (Exception e) {
			res.setError(e.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}