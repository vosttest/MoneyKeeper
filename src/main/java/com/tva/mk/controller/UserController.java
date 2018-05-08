package com.tva.mk.controller;

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
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String email = req.getEmail();
			String userName = req.getUserName();
			String password = req.getPassword();

			// Handle
			Users u = userService.getBy(userName, email);
			if (u == null) {
				res.setStatus("Fail");
				res.setMessage("Wrong user name or password!");
			} else {
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = userService.getRole(u.getId());
				String token = jwtTokenUtil.doGenerateToken(u, z);

				// Set data
				res.setResult(token);
			}
		} catch (AuthenticationException e) {
			res.setStatus("Fail");
			res.setMessage("Unauthorized/Invalid user name/email or password!");
		} catch (Exception ex) {
			res.setStatus("Fail");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserReq req) {
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
				res.setStatus("Fail");
				res.setMessage("User name or email have already registed!");
			}
		} catch (Exception ex) {
			res.setStatus("Fail");
			res.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}