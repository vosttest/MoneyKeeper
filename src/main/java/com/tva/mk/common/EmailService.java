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

	private static final String TEMPLATE_FORGOT_PWD_EMAIL = "<div style=\"font-family:arial;\">Hi {0},<br/>"
			+ "<p>We recently received a password reset request for your Money Keeper account login.<br/>"
			+ "If you would like to reset your password, please <a href=\"{1}\">click here</a>.</p>"
			+ "<p>If you did not request a password reset, please ignore this email.<br/>"
			+ "Your password won't change until you access the link above and create a new one.</p>"
			+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

	private static final String APP_BASE_URL = "https://mk-dev.herokuapp.com";

	private static final String SEND_GRID_KET = "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";

	public static void Sendemail(String to, String subject, String msg) {
		String frm = "mk@cws.com";
		Email eFrom = new Email(frm);
		String eSubject = subject;
		Email eto = new Email(to);
		Content content = new Content("text/html", msg);
		Mail mail = new Mail(eFrom, eSubject, eto, content);

		String skey = SEND_GRID_KET;

		if (skey == null || skey == "")
			return;

		SendGrid sg = new SendGrid(skey);

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);

			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void NotifyForForgottenPasswordStatic(String useremail, String token, String username) {
		try {
			String appBaseURL = APP_BASE_URL;

			StringBuilder forgotPasswordURl = new StringBuilder(appBaseURL);
			forgotPasswordURl.append("/#/forgot-password?token=");
			forgotPasswordURl.append(token);

			if (StringUtils.hasText(useremail)) {
				String subject = "Reset your Money Keeper Password";
				String content = MessageFormat.format(TEMPLATE_FORGOT_PWD_EMAIL, username, forgotPasswordURl);
				Sendemail(useremail, subject, content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}