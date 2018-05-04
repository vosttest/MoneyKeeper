package com.tva.mk.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

	// end
}