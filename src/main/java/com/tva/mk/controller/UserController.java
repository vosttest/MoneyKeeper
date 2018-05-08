package com.tva.mk.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.bll.UserService;
import com.tva.mk.config.JwtTokenUtil;
import com.tva.mk.model.Users;
import com.tva.mk.req.UserReq;
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
	public ResponseEntity<?> signIn(@RequestBody UserReq req) {
		SingleRsp rsp = new SingleRsp();

		try {
			// Get data
			String email = req.getEmail();
			String accountNo = req.getAccountNo();
			String userName = req.getUserName();
			String password = req.getPassword();

			// Handle
			Users u = userService.getUsersByUserNameOrEmailOrAccountNo(userName, email, accountNo);
			if (u == null) {
				rsp.setStatus("Fail");
				rsp.setMessage("Wrong user name or password!");
			} else {
				UsernamePasswordAuthenticationToken x = new UsernamePasswordAuthenticationToken(u.getUserName(),
						password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = userService.getRole(u.getId());
				String token = jwtTokenUtil.doGenerateToken(u, z);

				// Set data
				rsp.setResult(token);
			}
		} catch (AuthenticationException e) {
			rsp.setStatus("Fail");
			rsp.setMessage("Unauthorized / Invalid user name/email/account no or password!");
		} catch (Exception ex) {
			rsp.setStatus("Fail");
			rsp.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserReq req) {
		SingleRsp rsp = new SingleRsp();

		// Get data
		String userName = req.getUserName();
		String password = req.getPassword();
		String passwordHash = bCryptPasswordEncoder.encode(password);
		String firstName = req.getFirstName();
		String lastName = req.getLastName();
		String email = req.getEmail();
		String contactNo = req.getContactNo();
		String remark = req.getRemark();

		// Handle
		Users u = new Users();
		u.setContactNo(contactNo);
		u.setEmail(email);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setPasswordHash(passwordHash);
		u.setUserName(userName);
		u.setRemarks(remark);
		u.setIsEmailVerified(false);
		u.setStatus("ACT");
		u.setFailedAuthAttempts(0);
		u.setIsLocked(false);
		u.setIsDeleted(false);
		u.setCreateBy(0);
		u.setCreateOn(new Date());
		u.setModifyBy(0);
		u.setModifyOn(new Date());
		String tmp = userService.save(u);

		if (tmp == null) {
			rsp.setStatus("Fail");
			rsp.setMessage("User name or email have already registed!");
		} else {
			List<SimpleGrantedAuthority> z = userService.getRole(u.getId());
			String token = jwtTokenUtil.doGenerateToken(u, z);

			// Set Data
			rsp.setResult(token);
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	// end
}