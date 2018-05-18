package com.tva.mk.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.UserService;
import com.tva.mk.common.Utils;
import com.tva.mk.config.JwtTokenUtil;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.model.Users;
import com.tva.mk.req.BaseReq;
import com.tva.mk.req.UserChangePwdReq;
import com.tva.mk.req.UserForgotPwdReq;
import com.tva.mk.req.UserSignInReq;
import com.tva.mk.req.UserSignUpReq;
import com.tva.mk.req.UserUpdateReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/user")
public class UserController {
	// region -- Fields --

	@Autowired
	private UserService userService;

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

	@PostMapping("/profile-user")
	public ResponseEntity<?> inforUser(@RequestBody UserSignUpReq req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			// Get data
			// TO DO

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
	public ResponseEntity<?> updateUser(@RequestBody UserUpdateReq req, @RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();
		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();
			// Get data
			// TO DO

			Users m = userService.getBy(id);

			String firstName = req.getFirstName();
			String lastName = req.getLastName();
			String email = req.getEmail();
			String contactNo = req.getContactNo();
			String remarks = req.getRemarks();
			Date createOn=req.getCreateOn();
			int createBy=req.getCreateBy();
			String status=req.getStatus();

			// Set Data

			m.setContactNo(contactNo);
			m.setEmail(email);
			m.setFirstName(firstName);
			m.setLastName(lastName);
			m.setRemarks(remarks);
			m.setCreateOn(createOn);
			m.setCreateBy(createBy);
			m.setStatus(status);
			// Handle
			String tmp = userService.save(m);
			if (!tmp.isEmpty()) {
				res.setError("Can Not Update User");
			}

		} catch (

		Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	// end
}