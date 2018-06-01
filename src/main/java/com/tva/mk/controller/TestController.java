package com.tva.mk.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.SettingService;
import com.tva.mk.bll.UserService;
import com.tva.mk.common.Const;
import com.tva.mk.common.Utils;
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

	@GetMapping("/reset-auth")
	public ResponseEntity<?> resetAuth(@RequestParam(name = "id") String id) {
		BaseRsp res = new BaseRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				// Handle
				Users m = userService.getBy(id, id);
				settingService.resetAuth(m.getId());
			} else {
				res.setError("You can reset authentication in development mode only");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/active-code")
	public ResponseEntity<?> getActiveCode(@RequestParam(name = "id") String id) {
		SingleRsp res = new SingleRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				// Handle
				Users m = userService.getBy(id, id);
				Users m1 = userService.getActiveCode(m.getId());
				String data = m1.getActiveCode();

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
	public ResponseEntity<?> getToken(@RequestParam(name = "id") String id) {
		SingleRsp res = new SingleRsp();

		try {
			// Get environment variable
			String mod = System.getenv(Const.Mode.DEV);

			if (mod != null && "Y".equals(mod)) {
				// Handle
				Users m = userService.getBy(id, id);

				Date d = new Date();
				SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.FULL);
				f.setTimeZone(TimeZone.getTimeZone("GMT+7"));
				String text = f.format(d);

				f = new SimpleDateFormat(Const.DateTime.TOKEN);
				f.setTimeZone(TimeZone.getTimeZone("UTC"));
				String s = f.format(d);
				s += m.getUuid();
				int n = Const.Authentication.TOKEN_NUMBER;
				String token = text + " - " + Utils.getToken(s, n);

				// Set data
				res.setResult(token);
			} else {
				res.setError("You can get token in development mode only");
			}

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}