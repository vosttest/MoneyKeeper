package com.tva.mk.common;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Email service
 * 
 * @author T
 *
 */
public class SMSService {
	// region -- Methods --

	public static String sendSMS(String to, String msg) {
		try {
			String url = System.getenv(Const.SMS.SMS_URL);
			String smsUserName = System.getenv(Const.SMS.SMS_USERNAME);
			String smsPassword = System.getenv(Const.SMS.SMS_PASSWORD);

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			JSONObject body = new JSONObject();
			body.put("Phone", to);
			body.put("Message", msg);
			body.put("User", smsUserName);
			body.put("Pass", smsPassword);

			HttpEntity<String> request = new HttpEntity<String>(body.toString(), headers);
			ResponseEntity<String> res = restTemplate.postForEntity(url, request, String.class);

			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	// end
}