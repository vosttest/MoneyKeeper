package com.tva.mk.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
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

	private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// end

	// region -- Methods --

	public static Date dateFormatFromString(String stringDate) {
		Date d;
		try {
			d = FORMATTER.parse(stringDate);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<SimpleGrantedAuthority> getAuthorities(List<String> roles) {
		if (roles != null) {
			return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

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

	public static void NotifyForForgottenPassword(String userEmail, String token, String userFirstName) {
		EmailService.NotifyForForgottenPasswordStatic(userEmail, token, userFirstName);
	}

	/**
	 * Get password token expire time (current time + 5 minutes)
	 * 
	 * @return
	 */
	public static Date getPwdTokenExpiryTimeInUTC() throws Exception {
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

	public static boolean validateVerificationLinkToken(Date tokenExpiry) {
		TimeZone t = TimeZone.getTimeZone("UTC");
		Calendar t1 = Calendar.getInstance(t);

		if (tokenExpiry.compareTo(t1.getTime()) < 0) {
			return false;
		}

		return true;
	}

	// end
}