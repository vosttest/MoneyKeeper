package com.tva.mk.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tva.mk.dto.PayloadDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

public class Utils {
	// region -- Fields --

	// end

	// region -- Methods --

	/**
	 * Date format
	 * 
	 * @param date
	 * @return
	 */
	public static Date dateFormat(String date) {
		Date res = null;
		SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.FULL);

		try {
			res = f.parse(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Get authorities
	 * 
	 * @param roles
	 * @return
	 */
	public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
		if (roles != null) {
			return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	/**
	 * Get token information
	 * 
	 * @param header
	 * @return
	 */
	public static PayloadDto getTokenInfor(HttpHeaders header) {
		String token = header.get(Const.Authentication.HEADER_STRING).get(0);
		token = token.replace(Const.Authentication.TOKEN_PREFIX, "");

		JwtParser x = Jwts.parser().setSigningKey(Const.Authentication.SIGNING_KEY);
		Claims y = x.parseClaimsJws(token).getBody();
		Object z = y.get("user");

		ObjectMapper mapper = new ObjectMapper();
		PayloadDto res = mapper.convertValue(z, PayloadDto.class);
		return res;
	}

	/**
	 * Get UTC date time
	 * 
	 * @param type
	 *            Choose attribute to add (Calendar.MINUTE, Calendar.HOUR, ...)
	 * @param n
	 *            Number want to add
	 * @return
	 * @throws Exception
	 */
	public static Date getTime(int type, int n) throws Exception {
		Date res = null;

		try {
			TimeZone t = TimeZone.getTimeZone("UTC");
			Calendar c = Calendar.getInstance(t);
			c.add(type, n);
			res = c.getTime();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return res;
	}

	/**
	 * Verify with current date
	 * 
	 * @param d
	 *            Date
	 * @return
	 */
	public static boolean verify(Date d) {
		TimeZone t = TimeZone.getTimeZone("UTC");
		Calendar c = Calendar.getInstance(t);

		if (d.compareTo(c.getTime()) < 0) {
			return false;
		}

		return true;
	}

	/**
	 * Get token
	 * 
	 * @param l
	 *            length
	 * @return
	 */
	public static String getToken(int l) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuilder token = new StringBuilder(l);

		for (int i = 0; i < l; i++) {
			token.append(chars.charAt(random.nextInt(chars.length())));
		}

		return token.toString();
	}

	/**
	 * Get token with 5 digits
	 * 
	 * @return
	 */
	public static String getToken() {
		String res = "00000";

		Random t = new Random();
		String s = (t.nextInt(99999) + 1) + "";
		res = res.substring(0, 5 - s.length()) + s;

		return res;
	}
	
	/**
	 * Get token with 5 digits
	 * @param d Date time
	 * @return
	 */
	public static String getToken(Date d) {
		SimpleDateFormat f = new SimpleDateFormat(Const.DateTime.FULL);
		//TODO
		String res = "00000";

		Random t = new Random();
		String s = (t.nextInt(99999) + 1) + "";
		res = res.substring(0, 5 - s.length()) + s;

		return res;
	}

	// end
}