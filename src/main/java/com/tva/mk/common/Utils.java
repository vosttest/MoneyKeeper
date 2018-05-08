package com.tva.mk.common;

import static com.tva.mk.common.Constants.HEADER_STRING;
import static com.tva.mk.common.Constants.SIGNING_KEY;
import static com.tva.mk.common.Constants.TOKEN_PREFIX;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
		String token = header.get(HEADER_STRING).get(0);
		token = token.replace(TOKEN_PREFIX, "");
		JwtParser x = Jwts.parser().setSigningKey(SIGNING_KEY);
		Claims y = x.parseClaimsJws(token).getBody();

		PayloadDto res = (PayloadDto) y.get("user");
		return res;
	}

	// end
}