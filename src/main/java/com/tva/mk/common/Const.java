package com.tva.mk.common;

public class Const {
	public static final String STATUS_ACTIVE = "ACT";

	public static final String STATUS_INACTIVE = "INA";

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
		public static final String CODE_REMINDER = "SET01";

		public static final String CODE_CURRENCY = "SET02";

		public static final String DEFAULT_REMINDER = "20:00";

		public static final String DEFAULT_CURRENCY = "VND";
	}

	public class Mode {
		public static final String DEV = "DEV_MODE";

		public static final String RSA = "RSA_MODE";
	}

	public class Email {
		public static final String TEMPLATE_FORGOT_PWD_EMAIL = "<div style=\"font-family:arial;\">Hi {0},<br/>"
				+ "<p>We recently received a password reset request for your Money Keeper account login.<br/>"
				+ "If you would like to reset your password, please <a href=\"{1}\">click here</a>.</p>"
				+ "<p>If you did not request a password reset, please ignore this email.<br/>"
				+ "Your password won't change until you access the link above and create a new one.</p>"
				+ "<p>With regards,<br><strong>Money Keeper Team</strong></p></div>";

		public static final String APP_BASE_URL = "APP_BASE_URL";

		public static final String SENDGRID_API_KEY = "SENDGRID_API_KEY";

		public static final String SENDGRID_USERNAME = "SENDGRID_USERNAME";

		public static final String SENDGRID_PASSWORD = "SENDGRID_PASSWORD";

		public static final String FROM_EMAIL = "FROM_EMAIL";
	}
}