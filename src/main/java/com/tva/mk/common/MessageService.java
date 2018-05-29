package com.tva.mk.common;

import java.text.MessageFormat;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
	 * Send SMS
	 * 
	 * @param to
	 *            Phone number
	 * @param msg
	 *            Message
	 * @return
	 */
	public static String send(String to, String msg) {
		String res = "";

		try {
			if (StringUtils.hasText(to)) {
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

				HttpEntity<String> req = new HttpEntity<String>(body.toString(), h);
				ResponseEntity<String> rsp = t.postForEntity(url, req, String.class);

				res = rsp.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	/**
	 * Send email with active code template
	 * 
	 * @param to
	 *            Phone number
	 * @param name
	 *            First name
	 * @param code
	 *            Active code
	 * @return
	 */
	public static String getActiveCode(String to, String name, String code) {
		String template = Const.SMS.TEMPLATE_ACTIVE_CODE;
		String content = MessageFormat.format(template, name, code);
		return send(to, content);
	}

	// end
}