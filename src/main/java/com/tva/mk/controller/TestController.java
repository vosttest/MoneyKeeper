package com.tva.mk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.SettingService;
import com.tva.mk.bll.UserService;
import com.tva.mk.common.Const;
import com.tva.mk.common.Utils;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Users;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/test")
public class TestController {
	// region -- Fields --

	@Autowired
	private SettingService settingService;

	@Autowired
	private UserService userService;

	// end

	// region -- Methods --

	@PostMapping("/reset-auth")
	public ResponseEntity<?> resetAuth(@RequestHeader HttpHeaders header) {
		BaseRsp res = new BaseRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				PayloadDto pl = Utils.getTokenInfor(header);
				int id = pl.getId();

				// Handle
				settingService.resetAuth(id);
			} else {
				res.setError("You can reset authentication in development mode only");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/active-code")
	public ResponseEntity<?> getActiveCode(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				PayloadDto pl = Utils.getTokenInfor(header);
				int id = pl.getId();

				// Handle
				Users m = userService.getActiveCode(id);
				String data = m.getActivationCode();

				// Set data
				res.setResult(data);
			} else {
				res.setError("You can get active code in development mode only");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/reset-auth")
	public ResponseEntity<?> resetAuth(@RequestParam(name = "id") int id) {
		BaseRsp res = new BaseRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				// Handle
				settingService.resetAuth(id);
			} else {
				res.setError("You can reset authentication in development mode only");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/active-code")
	public ResponseEntity<?> getActiveCode(@RequestParam(name = "id") int id) {
		SingleRsp res = new SingleRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				// Handle
				Users m = userService.getActiveCode(id);
				String data = m.getActivationCode();

				// Set data
				res.setResult(data);
			} else {
				res.setError("You can get active code in development mode only");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/token")
	public ResponseEntity<?> getToken() {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			Date d = new Date();
			SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.FULL);
			String text = f.format(d);
			String token = text + " - " + Utils.getToken(d, 6);

			// Set data
			res.setResult(token);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}