package com.tva.mk.common;

public class Const {
	public static final String STATUS_ACTIVE = "ACT";

	public static final String STATUS_INACTIVE = "INA";

	public static final String APP_BASE_URL = "APP_BASE_URL";

	public static final String APP_FROM_EMAIL = "APP_FROM_EMAIL";

	public class Authentication {
		public static final long TOKEN_TIME = 24 * 60 * 60;

		public static final String SIGNING_KEY = "auth123key";

		public static final String TOKEN_PREFIX = "Bearer ";

		public static final String HEADER_STRING = "Authorization";

		public static final String ROLE_ADMIN = "Admin";

		public static final String RSA_PRIVATE = "RSA_PRIVATE_KEY";

		public static final String RSA_PUBLIC = "RSA_PUBLIC_KEY";
	}

	public class HTTP {
		public static final String STATUS_SUCCESS = "success";

		public static final String STATUS_ERROR = "error";
	}

	public class Type {
		public static final String Account = "Account";

		public static final String EXPENSE = "Expense";

		public static final String INCOME = "Income";

		public static final String SETTING = "Setting";

		public static final String CURRENCY = "Currency";

		public static final String TERM = "Term";

		public static final String INTEREST_PAID = "InterestPaid";

		public static final String TERM_END = "TermEnd";
	}

	public class User {
		public static final int FAILED_AUTH_ATTEMPTS = 2;
	}

	public class Setting {
		public static final String CODE_REMINDER = "SET001";

		public static final String CODE_CURRENCY = "SET002";

		public static final String CODE_LOGIN = "SET003";

		public static final String DEFAULT_REMINDER = "20:00";

		public static final String DEFAULT_CURRENCY = "VND";
	}

	public class Mode {
		public static final String DEV = "DEV_MODE";

		public static final String RSA = "RSA_MODE";
	}

	public class Email {
		public static final String TEMPLATE_FORGOT_PASSWORD = "<div style=\"font-family:arial;\">Hi {0},<br/>"
				+ "<p>We recently received a password reset request for your Money Keeper account login.<br/>"
				+ "If you would like to reset your password, please <a href=\"{1}\">click here</a>.</p>"
				+ "<p>If you did not request a password reset, please ignore this email.<br/>"
				+ "Your password won't change until you access the link above and create a new one.</p>"
				+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

		public static final String TEMPLATE_ACTIVATION_CODE = "<div style=\"font-family:arial;\">Hi {0},<br/>"
				+ "<p>We recently received a activation code request for your Money Keeper account login.<br/>"
				+ "If you would like to active your Token application, please use activation code <b>{1}</b>.</p>"
				+ "<p>If you did not request a Token application, please ignore this email.<br/>"
				+ "Your Token application won't change until you access the link above and create a new one.</p>"
				+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

		public static final String SENDGRID_API_KEY = "SENDGRID_API_KEY";
	}

	public class SMS {
		public static final String SMS_URL = "SMS_URL";

		public static final String SMS_USERNAME = "SMS_USERNAME";

		public static final String SMS_PASSWORD = "SMS_PASSWORD";
	}

	public class DateTime {
		public static final String FULL = "yyyy-MM-dd HH:mm:ss";
	}

	public class Activation {
		public static final String MAIL = "MAIL";

		public static final String SMS = "SMS";
	}
}