package com.tva.mk.common;

import static com.tva.mk.common.Constants.SIGNING_KEY;
import static com.tva.mk.common.Constants.TOKEN_PREFIX;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tva.mk.dto.PayloadDto;

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

	public static int getUserIdFromToken(String token) {
		PayloadDto res = (PayloadDto) Jwts.parser().setSigningKey(SIGNING_KEY)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().get("user");
		return res.getId();
	}

	// end
}