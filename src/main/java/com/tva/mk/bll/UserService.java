package com.tva.mk.bll;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.common.Const;
import com.tva.mk.common.EmailService;
import com.tva.mk.common.Enums;
import com.tva.mk.common.Utils;
import com.tva.mk.dal.AuthTokenDao;
import com.tva.mk.dal.RoleDao;
import com.tva.mk.dal.UserDao;
import com.tva.mk.dto.ProfileDto;
import com.tva.mk.model.AuthToken;
import com.tva.mk.model.Users;

@Service(value = "userService")
@Transactional
public class UserService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthTokenDao authTokenDao;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// end

	// region -- Methods --

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users u = userDao.getBy(userName);

		if (u == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		List<String> roles = roleDao.getRoleByUserId(u.getId());
		String hash = u.getPasswordHash();

		return new User(userName, hash, getAuthority(roles));
	}

	public List<SimpleGrantedAuthority> getRole(int id) {
		List<String> roles = roleDao.getRoleByUserId(id);
		List<SimpleGrantedAuthority> res = getAuthority(roles);
		return res;
	}

	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	public Users getBy(int id) {
		Users res = userDao.getBy(id);
		return res;
	}

	public Users getBy(String userName, String email) {
		Users res = userDao.getBy(userName, email);
		return res;
	}

	public String save(Users m) {
		String res = "";

		Integer id = m.getId();
		String userName = m.getUserName();
		String email = m.getEmail();

		Users m1;
		if (id == null || id == 0) {
			m1 = userDao.getBy(userName, email);
			if (m1 != null) {
				res = "Duplicate data";
			} else {
				m.setCreateBy(1);
				m.setCreateOn(new Date());

				userDao.save(m);
			}
		} else {
			m1 = userDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m1.setModifyBy(1);
				m1.setModifyOn(new Date());

				m1.setFirstName(m.getFirstName());
				m1.setLastName(m.getLastName());
				m1.setAccountNo(m.getAccountNo());
				m1.setContactNo(m.getContactNo());
				m1.setEmail(m.getEmail());
				m1.setRemarks(m.getRemarks());

				userDao.save(m1);
			}
		}

		return res;
	}

	public ProfileDto getProfile(int id) {
		ProfileDto res = new ProfileDto();

		Users m = userDao.getBy(id);
		res.setContactNo(m.getContactNo());
		res.setEmail(m.getEmail());
		res.setFirstName(m.getFirstName());
		res.setLastName(m.getLastName());
		res.setRemarks(m.getRemarks());
		res.setUserName(m.getUserName());

		return res;
	}

	public String delete(int id) {
		String res = "";

		Users m = userDao.getBy(id);
		if (m != null) {
			m.setDeleted(true);
			userDao.save(m);
		}

		return res;
	}

	public Users forgotPassword(String password, String token) throws Exception {
		Users m = userDao.getByToken(token);
		if (m == null) {
			throw new Exception("Invalid token, no such token allocated to a user!");
		}

		Date t = m.getPassReminderExpire();
		if (!Utils.verify(t)) {
			throw new Exception("Invalid token , token has expired");
		}

		m.setPasswordHash(password);
		m.setPassReminderExpire(null);
		m.setPassReminderToken(null);
		m.setModifyOn(new Date());
		m.setModifyBy(m.getId());

		userDao.save(m);

		return m;
	}

	public void verifyMail(String email) throws Exception {
		Users m = getBy("", email);
		if (m == null) {
			throw new Exception("Email doesn't exist!");
		}

		// Generate password reminder token
		String token;
		try {
			StringBuilder t = new StringBuilder("");
			t.append(email);
			t.append(m.getUserName());
			token = bCryptPasswordEncoder.encode(t);
		} catch (Exception e) {
			throw new Exception("Failed to generate password_reminder_token");
		}

		// Get password reminder token expire time
		Date expire = Utils.getTime(Calendar.MINUTE, 5);
		if (expire == null) {
			throw new Exception("Failed to generate Password Reminder Token Expiry Time");
		}

		m.setPassReminderExpire(expire);
		m.setPassReminderToken(token);

		userDao.save(m);

		EmailService.forgotPassword(email, token, m.getFirstName());
	}

	/**
	 * Generate token/OTP
	 * 
	 * @param module
	 *            Token/OTP of action (sign-in, transaction, ...)
	 * @param userId
	 * @param type
	 *            TOKEN or OTP or empty
	 * @return
	 * @throws Exception
	 */
	public AuthToken generateToken(String module, int userId, String type) throws Exception {
		AuthToken m = authTokenDao.getBy("", module, userId);

		if (m == null) {
			m = new AuthToken();
		}

		m.setCreateBy(userId);
		m.setCreateOn(new Date());

		String clientKey = bCryptPasswordEncoder.encode(new Date().toString());
		m.setClientKey(clientKey);

		int n = Const.Authentication.TOKEN_NUMBER;
		String token = "";
		switch (type) {
		case Const.Setting.CODE_TOKEN:
			Date d = new Date();
			SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.TOKEN);
			f.setTimeZone(TimeZone.getTimeZone("UTC"));
			String s = f.format(d);

			token = Utils.getToken(s, n);
			break;

		case Const.Setting.CODE_OTP:
			token = Utils.getToken();
			break;

		default:
			token = Utils.getToken(n);
			break;
		}
		m.setToken(token);

		m.setModule(module);
		Date d = Utils.getTime(Calendar.MINUTE, Const.Authentication.TOKEN_MINUTE);
		m.setExpireOn(d);

		// Reset data
		m.setModifyBy(null);
		m.setVerified(false);
		m.setModifyOn(null);

		authTokenDao.save(m);

		return m;
	}

	public void verifyToken(String clientKey, int userId, String token) throws Exception {
		AuthToken m = authTokenDao.getBy(clientKey, "", userId);

		if (m == null) {
			throw new Exception(Enums.Error.E201.toString());
		}

		Date t = m.getExpireOn();
		if (!Utils.verify(t)) {
			throw new Exception(Enums.Error.E202.toString());
		}

		String authKey = m.getToken();
		if (!authKey.equals(token) || authKey == null) {
			throw new Exception(Enums.Error.E203.toString());
		}

		m.setVerified(true);
		m.setModifyOn(new Date());
		m.setModifyBy(userId);

		authTokenDao.save(m);
	}

	public Users getActiveCode(int id) {
		Users res = null;

		try {
			res = userDao.getBy(id);
			if (res != null) {
				res.setModifyBy(id);
				res.setModifyOn(new Date());

				Date t = Utils.getTime(Calendar.HOUR, 1);
				res.setActivationExpire(t);
				int n = Const.Authentication.ACTIVE_NUMBER;
				String c = Utils.getToken(n);
				res.setActiveCode(c);

				userDao.save(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	public Users verifyActiveCode(String code) {
		Users res = null;

		try {
			res = userDao.getByActiveCode(code);
			if (res != null) {
				res.setModifyOn(new Date());
				res.setActivationExpire(null);
				res.setActiveCode(null);

				userDao.save(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	// end
}