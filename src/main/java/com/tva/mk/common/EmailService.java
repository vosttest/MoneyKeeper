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

	public static void Sendemail(String to, String subject, String msg) {
		String frm = "mk@cws.com";
		Email eFrom = new Email(frm);
		String eSubject = subject;
		Email eto = new Email(to);
		Content content = new Content("text/html", msg);
		Mail mail = new Mail(eFrom, eSubject, eto, content);

		String skey = "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";

		if (skey == null || skey == "")// its just for testing
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

	/*
	 * public void NotifyForForgottenPasswordDynamic(String userEmail, String token,
	 * String userFirstName) { EmailTemplate emailTemplate; EmailTemplateDto
	 * emailTemplateDto;
	 * 
	 * try { String appBaseURL = "https://mk-dev.herokuapp.com"; if
	 * (StringUtils.hasText(appBaseURL)) {
	 * 
	 * StringBuilder forgotPasswordURl = new StringBuilder(appBaseURL);
	 * forgotPasswordURl.append("/#/forgot-password?token=");
	 * forgotPasswordURl.append(token);
	 * 
	 * Map<String, Object> emailData = new HashMap<>(); emailData.put("[FirstName]",
	 * userFirstName); emailData.put("[ForgotPasswordUrl]", forgotPasswordURl);
	 * 
	 * if (!StringUtils.hasText(userEmail)) throw new
	 * Exception("No UserName found"); emailTemplate =
	 * emailTemplateService.getEmailTemplateByName("NotifyForForgottenPassword");
	 * emailTemplateDto = generateEmailContent(emailData, emailTemplate);
	 * commonServices.Sendemail(userEmail, emailTemplateDto.getSubject(),
	 * emailTemplateDto.getBody()); }
	 * 
	 * } catch (Exception exception) { exception.printStackTrace();
	 * LOGGER.severe("Faled to send mail, " + exception.getMessage()); } }
	 */

	public static void NotifyForForgottenPasswordStatic(String useremail, String token, String username) {
		try {
			String appBaseURL = "https://mk-dev.herokuapp.com";

			StringBuilder forgotPasswordURl = new StringBuilder(appBaseURL);
			forgotPasswordURl.append("/#/forgot-password?token=");
			forgotPasswordURl.append(token);

			if (StringUtils.hasText(useremail)) {
				String subject = "Reset your Money Keeper Password";
				String content = MessageFormat.format("<div style=\"font-family:arial;\">Hi {0},<br/>"
						+ "<p>We recently received a password reset request for your Money Keeper account login.<br/>"
						+ "If you would like to reset your password, please <a href=\"{1}\">click here</a>.</p>"
						+ "<p>If you did not request a password reset, please ignore this email.<br/>"
						+ "Your password won't change until you access the link above and create a new one.</p>"
						+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>", username,
						forgotPasswordURl);
				Sendemail(useremail, subject, content);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}