package com.tva.mk.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
		String chars = "0123456789";
		Random random = new Random();
		StringBuilder token = new StringBuilder(l);

		for (int i = 0; i < l; i++) {
			token.append(chars.charAt(random.nextInt(chars.length())));
		}

		return token.toString();
	}

	/**
	 * Get token with 6 digits
	 * 
	 * @return
	 */
	public static String getToken() {
		int n = Const.Authentication.TOKEN_NUMBER;
		int max = (int) Math.pow(10, n) - 1;

		Random t = new Random();
		int s = t.nextInt(max);

		char[] zeros = new char[n];
		Arrays.fill(zeros, '0');
		String format = String.valueOf(zeros);
		DecimalFormat df = new DecimalFormat(format);
		String res = df.format(s);

		return res;
	}

	/**
	 * Get token
	 * 
	 * @param s
	 *            String data
	 * @param num
	 *            Number of digits will get
	 * @return
	 */
	public static String getToken(String s, int num) {
		String res = "";

		s = Const.Authentication.TOKEN_KEY1 + s + Const.Authentication.TOKEN_KEY2;
		String hash = generateSHA256(s);
		String[] arr = hash.split(Const.SpecialString.Minus);

		if (num > 8 || num < 1) {
			num = 8;
		}

		for (String i : arr) {
			if (num == 0) {
				break;
			}

			eachHash: for (char item : i.toCharArray()) {
				if (Character.isDigit(item)) {
					res += item;
					break eachHash;
				}
			}

			num--;
		}

		return res;
	}

	/**
	 * Generate SHA-256
	 * 
	 * @param s
	 *            String data
	 * @return
	 */
	private static String generateSHA256(String s) {
		String res = "";

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < encodedhash.length; i++) {
				String hex = Integer.toHexString(0xff & encodedhash[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
				if (i % 4 == 3) {
					hexString.append(Const.SpecialChar.Minus);
				}
			}

			res = hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static Map<String, Object> readXML(String xml) throws Exception {
		Map<String, Object> res = new LinkedHashMap<>();

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Exrate");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					res.put(eElement.getAttribute("CurrencyCode"), eElement.getAttribute("Transfer"));
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return res;
	}

	// end
}