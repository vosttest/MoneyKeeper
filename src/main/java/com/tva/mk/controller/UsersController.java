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

import com.tva.mk.bll.UsersService;
import com.tva.mk.config.JwtTokenUtil;
import com.tva.mk.model.Users;
import com.tva.mk.req.UsersReq;
import com.tva.mk.rsp.BaseRsp;
import com.tva.mk.rsp.SingleRsp;

@RestController
@RequestMapping("/users")
public class UsersController {
	// region -- Fields --

	@Autowired
	private UsersService usersService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	// end

	// region -- Methods --

	@PostMapping("/signIn")
	public ResponseEntity<?> signIn(@RequestBody UsersReq req) {
		SingleRsp rsp = new SingleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();

			// Handle
			Users u = usersService.getUsersByUserName(userName);
			if (u == null) {
				rsp.setCallStatus("Fail");
				rsp.setMessage("Wrong user name or password!");
			} else {
				UsernamePasswordAuthenticationToken x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);

				List<SimpleGrantedAuthority> z = usersService.getRole(u.getId());
				String token = jwtTokenUtil.doGenerateToken(u, z);

				// Set data
				rsp.setResult(token);
			}
		} catch (AuthenticationException e) {
			rsp.setCallStatus("Fail");
			rsp.setMessage("Unauthorized / Invalid email or password!");
		} catch (Exception ex) {
			rsp.setCallStatus("Fail");
			rsp.setMessage(ex.getMessage());
		}

		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	@PostMapping("/signUp")
	public ResponseEntity<?> signUp(@RequestBody UsersReq req) {
		BaseRsp rsp = new BaseRsp();

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
		u.setPassword(passwordHash);
		u.setUserName(userName);
		u.setRemark(remark);
		u.setStatus("DAC");
		u.setIsDeleted(false);
		u.setCreateBy(0);
		u.setCreateOn(new Date());
		u.setModifyBy(0);
		u.setModifyOn(new Date());
		String tmp = usersService.save(u);

		// Set Data
		if (tmp == null) {
			rsp.setCallStatus("Fail");
			rsp.setMessage("User name or email have already register!");
		}
		return new ResponseEntity<>(rsp, HttpStatus.OK);
	}

	// end
}