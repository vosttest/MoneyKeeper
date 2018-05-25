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
public class MessageService {
	// region -- Methods --

	/**
	 * Send SMS with activation code
	 * 
	 * @param to
	 *            Phone number
	 * @param msg
	 *            Message
	 * @return
	 */
	public static String getActiveCode(String to, String msg) {
		try {
			String url = System.getenv(Const.SMS.SMS_URL);
			String userName = System.getenv(Const.SMS.SMS_USERNAME);
			String password = System.getenv(Const.SMS.SMS_PASSWORD);

			RestTemplate t = new RestTemplate();
			HttpHeaders h = new HttpHeaders();
			h.setContentType(MediaType.APPLICATION_JSON);

			JSONObject body = new JSONObject();
			body.put("Phone", to);
			body.put("Message", msg);
			body.put("User", userName);
			body.put("Pass", password);

			HttpEntity<String> request = new HttpEntity<String>(body.toString(), h);
			ResponseEntity<String> res = t.postForEntity(url, request, String.class);

			return res.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	// end
}