package com.tva.mk.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	private static final SimpleDateFormat FARMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static Date dateFormatFromString(String stringDate) {
		Date d;
		try {
			d = FARMATTER.parse(stringDate);
	        return d;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}