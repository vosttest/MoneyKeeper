package com.tva.mk.bll;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.RoleDao;
import com.tva.mk.dal.UsersDao;
import com.tva.mk.model.Users;

@Service(value = "usersService")
@Transactional
public class UsersService implements UserDetailsService {
	// region -- Fields --

	@Autowired
	private UsersDao dao;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private RoleDao daoRole;

	// end

	// region -- Methods --

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Users u = dao.getUsersByUserName(userName);

		if (u == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		List<String> roles = daoRole.getRoleByUserId(u.getId());
		String hash = u.getPasswordHash();

		return new org.springframework.security.core.userdetails.User(userName, hash, getAuthority(roles));
	}

	public List<SimpleGrantedAuthority> getRole(int id) {
		List<String> roles = daoRole.getRoleByUserId(id);
		List<SimpleGrantedAuthority> res = getAuthority(roles);
		return res;
	}

	private List<SimpleGrantedAuthority> getAuthority(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
	}

	public Users getUsersByUserName(String userName) {
		Users u = dao.getUsersByUserName(userName);
		return u;
	}

	public Users getUsersById(int id) {
		Users u = dao.getUsersById(id);
		return u;
	}

	public String save(Users newU) {
		Users tmp;
		if (newU.getId() == null) {
			tmp = dao.getUsersByUserName(newU.getUserName());
			if (tmp != null) {
				return null;
			}
			tmp = dao.save(newU);
		} else {
			tmp = dao.getUsersById(newU.getId());
			if (tmp == null) {
				return null;
			}
			tmp = entityManager.merge(newU);
		}
		return tmp.getId().toString();
	}

	public String delete(int id) {
		Users tmp = dao.getUsersById(id);
		if (tmp != null) {
			tmp.setStatus("DEA");
			tmp = entityManager.merge(tmp);
			return tmp.getId().toString();
		}
		return null;
	}

	// end
}