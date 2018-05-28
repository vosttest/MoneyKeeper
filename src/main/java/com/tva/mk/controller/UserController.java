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
import com.tva.mk.common.Const;
import com.tva.mk.common.EmailService;
import com.tva.mk.common.MessageService;
import com.tva.mk.common.Utils;
import com.tva.mk.config.JwtTokenUtil;
import com.tva.mk.dto.PayloadDto;
import com.tva.mk.dto.ProfileDto;
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

	/**
	 * Request new token to log in (just and only when enable login authentication,
	 * user name & password have existed in db)
	 * 
	 * @param req
	 *            include (user name, password, client key, token)
	 * @return
	 */
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody UserSignInReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Get data
			String userName = req.getUserName();
			String password = req.getPassword();
			String clientKey = req.getClienKey();
			String token = req.getToken();
			boolean sendToken = req.isSendToken();

			// Handle
			Users m = userService.getBy(userName, userName);
			if (m == null) {
				res.setError("User name doesn't exist!");
			} else {
				userName = m.getUserName();
				UsernamePasswordAuthenticationToken x;
				x = new UsernamePasswordAuthenticationToken(userName, password);
				Authentication y = authenticationManager.authenticate(x);
				SecurityContextHolder.getContext().setAuthentication(y);
				int userId = m.getId();

				// Set data
				Map<String, Object> data = new LinkedHashMap<>();
				if (sendToken) {
					Setting t1 = settingService.getBy(userId, Const.Setting.CODE_LOGIN);
					String t2 = "";
					if (t1 != null) {
						t2 = t1.getValue() + "";
					}

					com.tva.mk.model.Authentication m1 = null;
					switch (t2) {
					case Const.Setting.CODE_TOKEN:
						m1 = userService.generateToken(Const.Module.SIGN_IN, userId, t2);
						String t3 = m1.getClientKey();
						data.put("authen", true);
						data.put("key", t3);
						break;

					case Const.Setting.CODE_OTP:
						m1 = userService.generateToken(Const.Module.SIGN_IN, userId, t2);
						t3 = m1.getClientKey();

						// Send SMS
						String t4 = m.getContactNo();
						String t5 = m1.getAuthKey();
						MessageService.getActiveCode(t4, t5);

						data.put("authen", true);
						data.put("key", t3);
						break;

					default:
						List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
						t3 = jwtTokenUtil.doGenerateToken(m, z);
						data.put("authen", false);
						data.put("key", t3);
						break;
					}
				} else {
					userService.verifyToken(clientKey, userId, token);

					List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
					String t1 = jwtTokenUtil.doGenerateToken(m, z);
					data.put("key", t1);
				}
				res.setResult(data);
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
			String firstName = req.getFirstName();
			String lastName = req.getLastName();
			String email = req.getEmail();
			String contactNo = req.getContactNo();
			String remarks = req.getRemarks();

			String password = req.getPassword();
			password = bCryptPasswordEncoder.encode(password);

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

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody UserSignUpReq req, @RequestHeader HttpHeaders header) {
		BaseRsp res = new BaseRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			String userName = req.getUserName();
			String firstName = req.getFirstName();
			String lastName = req.getLastName();
			String email = req.getEmail();
			String contactNo = req.getContactNo();
			String remarks = req.getRemarks();

			// Convert data
			Users m = new Users();
			m.setId(id);
			m.setContactNo(contactNo);
			m.setEmail(email);
			m.setFirstName(firstName);
			m.setLastName(lastName);
			m.setUserName(userName);
			m.setRemarks(remarks);

			// Handle
			String tmp = userService.save(m);

			if (!tmp.isEmpty()) {
				res.setError("Can not update user");
			}
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			ProfileDto m = userService.getProfile(id);

			// Set data
			res.setResult(m);
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

	/**
	 * Get active code to access token application
	 * 
	 * @param header
	 * @param req
	 * @return
	 */
	@PostMapping("/active-code")
	public ResponseEntity<?> getActiveCode(@RequestHeader HttpHeaders header, @RequestBody BaseReq req) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Get data
			String keyword = req.getKeyword();

			// Handle
			Users m = userService.getActiveCode(id);
			String data = "";
			if (Const.Activation.SMS.equals(keyword)) {
				MessageService.getActiveCode(m.getContactNo(), m.getActivationCode());
				data = "Sent to your phone";
			} else if (Const.Activation.MAIL.equals(keyword)) {
				EmailService.getActiveCode(m.getEmail(), m.getActivationCode(), m.getFirstName());
				data = "Sent to your email";
			} else {
				res.setError("Cannot send activation code");
			}

			// Set data
			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Verify active code to access token application
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/verify-active-code")
	public ResponseEntity<?> verifyActiveCode(@RequestBody BaseReq req) {
		SingleRsp res = new SingleRsp();

		try {
			// Get data
			String keyword = req.getKeyword();

			// Handle
			Users m = userService.verifyActiveCode(keyword);

			String token = "";
			if (m != null) {
				List<SimpleGrantedAuthority> z = userService.getRole(m.getId());
				token = jwtTokenUtil.doGenerateToken(m, z);
			} else {
				res.setError("Activation code wrong");
			}

			// Set data
			res.setResult(token);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/token-otp")
	public ResponseEntity<?> getTokenOtp(@RequestHeader HttpHeaders header) {
		SingleRsp res = new SingleRsp();

		try {
			PayloadDto pl = Utils.getTokenInfor(header);
			int id = pl.getId();

			// Handle
			com.tva.mk.model.Authentication m = userService.generateToken(Const.Module.SIGN_IN, id, null);

			// Set data
			res.setResult(m.getAuthKey());
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}