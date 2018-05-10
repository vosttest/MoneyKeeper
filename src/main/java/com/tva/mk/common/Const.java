package com.tva.mk.common;

public class Const {
	public class Authentication {
		public static final long TOKEN_TIME = 24 * 60 * 60;

		public static final String SIGNING_KEY = "auth123key";

		public static final String TOKEN_PREFIX = "Bearer ";

		public static final String HEADER_STRING = "Authorization";

		public static final String ROLE_ADMIN = "Admin";
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
		public static final String STATUS_ACTIVE = "ACT";

		public static final int FAILED_AUTH_ATTEMPTS = 2;
	}
}