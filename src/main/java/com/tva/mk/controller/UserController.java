package com.tva.mk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.SettingService;
import com.tva.mk.bll.UserService;
import com.tva.mk.common.Utils;
import com.tva.mk.config.JwtTokenUtil;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Setting;
import com.tva.mk.model.Users;
import com.tva.mk.req.BaseReq;
import com.tva.mk.req.UserChangePwdReq;
import com.tva.mk.req.UserForgotPwdReq;
import com.tva.mk.req.UserSignInReq;
import com.tva.mk.req.UserSignUpReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.MultipleRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/user")
public class UserController {
	// region -- Fields --

	@Autowired
	private UserService userService;

	@Autowired
	private SettingService settingService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// end

	// region -- Methods --

	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody UserSignInReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();

			// Handle
			Users m = userService.getBy(userName, userName);
			if (m == null) {
				res.setError("Wrong user name or password!");
			} else {
				userName = m.getUserName();
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
				String token = jwtTokenUtil.doGenerateToken(m, z);

				// Set data
				res.setResult(token);
			}
		} catch (AuthenticationException e) {
			res.setError("Unauthorized/Invalid user name/email or password!");
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserSignUpReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();
			password = bCryptPasswordEncoder.encode(password);
			String firstName = req.getFirstName();
			String lastName = req.getLastName();
			String email = req.getEmail();
			String contactNo = req.getContactNo();
			String remarks = req.getRemarks();

			// Convert data
			Users m = new Users();
			m.setContactNo(contactNo);
			m.setEmail(email);
			m.setFirstName(firstName);
			m.setLastName(lastName);
			m.setPasswordHash(password);
			m.setUserName(userName);
			m.setRemarks(remarks);

			// Handle
			String tmp = userService.save(m);

			if (tmp.isEmpty()) {
				List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
				String token = jwtTokenUtil.doGenerateToken(m, z);

				// Set Data
				res.setResult(token);
			} else {
				res.setError("User name or email have already registed!");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/verify-mail")
	public ResponseEntity<?> verifyMail(@RequestBody BaseReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Get data
			String email = req.getKeyword();

			// Handle
			userService.verifyMail(email);

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody UserForgotPwdReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String token = req.getToken();
			String password = req.getPassword();
			password = bCryptPasswordEncoder.encode(password);

			// Handle
			Users m = userService.forgotPassword(password, token);

			List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
			token = jwtTokenUtil.doGenerateToken(m, z);

			// Set data
			res.setResult(token);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/change-password")
	public ResponseEntity<?> changePassword(@RequestBody UserChangePwdReq req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			String oldPassword = req.getOldPassword();
			String password = req.getNewPassword();
			password = bCryptPasswordEncoder.encode(password);

			// Authenticate with old password
			Users m = userService.getBy(id);
			String userName = m.getUserName();
			UsernamePasswordAuthenticationToken x;
			x = new UsernamePasswordAuthenticationToken(userName, oldPassword);
			Authentication y = authenticationManager.authenticate(x);
			SecurityContextHolder.getContext().setAuthentication(y);

			// Handle
			m.setPasswordHash(password);
			String tmp = userService.save(m);
			if (!tmp.isEmpty()) {
				res.setError("Can Not Update Password ...");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/profile")
	public ResponseEntity<?> inforUser(@RequestBody UserSignUpReq req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			Users m = userService.getBy(id);
			Map<String, Object> data = new LinkedHashMap<>();

			// Set data
			data.put("info", m);
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/update-user")
	public ResponseEntity<?> updateUser(@RequestBody UserSignUpReq req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			String firstName = req.getFirstName();
			String lastName = req.getLastName();
			String email = req.getEmail();
			String contactNo = req.getContactNo();
			String remarks = req.getRemarks();
			String accountNo = req.getAccountNo();

			// Set Data
			Users m = userService.getBy(id);
			m.setContactNo(contactNo);
			m.setEmail(email);
			m.setFirstName(firstName);
			m.setLastName(lastName);
			m.setRemarks(remarks);
			m.setAccountNo(accountNo);

			// Handle
			String tmp = userService.save(m);
			if (!tmp.isEmpty()) {
				res.setError("Can Not Update User");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Use for receive new active code to access mobile app
	 * 
	 * @param header
	 * @return
	 */
	@GetMapping("/resend-active-code")
	public ResponseEntity<?> resendActiveCode(@RequestHeader HttpHeaders header) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			userService.resendActiveCode(id);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Request new token to log in (just and only when enable login authentication)
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/sign-in1")
	public ResponseEntity<?> sendToken(@RequestBody UserSignInReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();
			String clientKey = req.getClienKey(); // empty -> create

			// Handle
			Users m = userService.getBy(userName, userName);
			if (m == null) {
				res.setError("User name doenn't exist!");
			} else {
				userName = m.getUserName();
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				int userId = m.getId();
				List<Setting> t = settingService.search(userId);
				String t1 = t.get(2).getValue();
				Map<String, Object> t3 = new LinkedHashMap<>();

				switch (t1) {
				case "OTP":
				case "TOKEN":
					String t2 = userService.generateToken(clientKey, userId); // create new token and return client key
					t3.put("authen", "T");
					t3.put("key", t2);
					res.setResult(t3);
					break;
				default:
					List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
					t2 = jwtTokenUtil.doGenerateToken(m, z);
					t3.put("authen", "F");
					t3.put("key", t2);
					res.setResult(t3);
					break;
				}

			}
		} catch (AuthenticationException e) {
			res.setError("Unauthorized/Invalid user name/email or password!");
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Use for user had turn on login authentication
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/sign-in2")
	public ResponseEntity<?> signIn2(@RequestBody UserSignInReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();
			String clientKey = req.getClienKey();
			String token = req.getToken();

			// Handle
			Users m = userService.getBy(userName, userName);
			if (m == null) {
				res.setError("User name doenn't exist!");
			} else {
				userName = m.getUserName();
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				int userId = m.getId();

				userService.tokenAuthenticationValid(clientKey, userId, token);

				List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
				String t2 = jwtTokenUtil.doGenerateToken(m, z);

				res.setResult(t2);
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}