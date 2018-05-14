package com.tva.mk.common;

import java.io.IOException;
import java.text.MessageFormat;

import org.springframework.util.StringUtils;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailService {
	public static void sendMail(String to, String subject, String msg) {
		try {
			String from = System.getenv(Const.Email.FROM_EMAIL);

			Email eFrom = new Email(from);
			Email eTo = new Email(to);
			Content content = new Content("text/html", msg);
			Mail mail = new Mail(eFrom, subject, eTo, content);

			String skey = System.getenv(Const.Email.SENDGRID_API_KEY);
			if (skey == null || skey == "") {
				return;
			}

			Request req = new Request();
			req.setMethod(Method.POST);
			req.setEndpoint("mail/send");
			req.setBody(mail.build());

			SendGrid sg = new SendGrid(skey);
			Response rsp = sg.api(req);

			System.out.println(rsp.getStatusCode());
			System.out.println(rsp.getBody());
			System.out.println(rsp.getHeaders());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void notifyForgottenPassword(String email, String token, String firstName) {
		try {
			String url = System.getenv(Const.Email.APP_BASE_URL);
			String template = Const.Email.TEMPLATE_FORGOT_PWD_EMAIL;

			StringBuilder t = new StringBuilder(url);
			t.append("/#/forgot-password?token=");
			t.append(token);

			if (StringUtils.hasText(email)) {
				String subject = "Reset your Money Keeper Password";
				String content = MessageFormat.format(template, firstName, t);
				sendMail(email, subject, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}