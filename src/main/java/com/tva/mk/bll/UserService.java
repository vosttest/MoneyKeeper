package com.tva.mk.bll;

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

import com.tva.mk.common.Utils;
import com.tva.mk.dal.RoleDao;
import com.tva.mk.dal.UserDao;
import com.tva.mk.model.Users;

@Service(value = "userService")
@Transactional
public class UserService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private UserDao userDao;

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

				m1 = userDao.save(m);
			}
		} else {
			m1 = userDao.getBy(id);
			if (m1 == null) {
				res = "Id does not exist";
			} else {
				m.setModifyBy(1);
				m.setModifyOn(new Date());

				m1 = entityManager.merge(m);
			}
		}

		return res;
	}

	public String delete(int id) {
		String res = "";

		Users m = userDao.getBy(id);
		if (m != null) {
			m.setIsDeleted(true);
			userDao.save(m);
		}

		return res;
	}

	public boolean sendVerificationLinkMail(String email) throws Exception {
		Users m = getBy("", email);
		if (m == null) {
			throw new Exception("Email doesn't exist!");
		}

		/* Generate Password Reminder Token */
		String pwdReminderToken;
		try {
			StringBuilder token = new StringBuilder("");
			token.append(m.getEmail());
			token.append(m.getUserName());
			pwdReminderToken = bCryptPasswordEncoder.encode(token);
		} catch (Exception e) {
			throw new Exception("Failed to generate password_reminder_token");
		}

		/* Get PasswordReminder Token Expire Time */
		Date tokenExpiryTime = getPwdTokenExpiryTimeInUTC();
		if (tokenExpiryTime == null) {
			throw new Exception("Failed to generate Password Reminder Token Expiry Time");
		}

		/*
		 * update user table with password_reminder_token and password_reminder_expire
		 */

		m.setPassReminderExpire(tokenExpiryTime);
		m.setPassReminderToken(pwdReminderToken);

		userDao.save(m);

		/* Send mail */
		Utils.NotifyForForgottenPassword(m.getEmail(), pwdReminderToken, m.getFirstName());

		return true;
	}

	/**
	 * Get password token expire time (current time + 5 minutes)
	 * 
	 * @return
	 */
	private Date getPwdTokenExpiryTimeInUTC() throws Exception {
		Date res = null;

		try {
			TimeZone t = TimeZone.getTimeZone("UTC");
			Calendar t1 = Calendar.getInstance(t);
			t1.add(Calendar.MINUTE, 5);
			res = t1.getTime();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return res;
	}

	// end
}