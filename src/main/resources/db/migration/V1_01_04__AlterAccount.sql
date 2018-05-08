ALTER TABLE "account"
	ADD COLUMN "currency"			VARCHAR(64),
	ADD COLUMN "bank"				VARCHAR(128),
	ADD COLUMN "start_date"			TIMESTAMP,
	ADD COLUMN "term"				VARCHAR(64),
	ADD COLUMN "interest_rate"		FLOAT8,
	ADD COLUMN "interest_rates"		FLOAT8,
	ADD COLUMN "interest_paid"		VARCHAR(64),
	ADD COLUMN "term_ended"			VARCHAR(64),
	ADD COLUMN "from_account"		INT4;