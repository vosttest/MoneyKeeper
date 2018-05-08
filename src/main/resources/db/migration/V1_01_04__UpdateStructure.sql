ALTER TABLE "account"
	ADD COLUMN "currency" VARCHAR(64),
	ADD COLUMN "bank" VARCHAR(128),
	ADD COLUMN "start_date" timestamp,
	ADD COLUMN "term" VARCHAR(64),
	ADD COLUMN "interest_rate" FLOAT8,
	ADD COLUMN "interest_rates" FLOAT8,
	ADD COLUMN "interest_paid" VARCHAR(64),
	ADD COLUMN "term_ended" VARCHAR(64),
	ADD COLUMN "from_account" INT4;

INSERT INTO PUBLIC."common" ("type", "value", "text", "description", "sequence") VALUES
	('Currency', 'CUR01', 'VND', NULL, 1, NULL),
	('Currency', 'CUR02', 'USD', NULL, 2, NULL),
	('Currency', 'CUR03', 'EUR', NULL, 3, NULL),
	
	('Term', 'TRM01', '1 Week', NULL, 1, NULL),
	('Term', 'TRM02', '2 Weeks', NULL, 2, NULL),
	('Term', 'TRM03', '3 Weeks', NULL, 3, NULL),
	('Term', 'TRM04', '1 Month', NULL, 4, NULL),
	('Term', 'TRM05', '3 Months', NULL, 5, NULL),
	('Term', 'TRM06', '6 Months', NULL, 6, NULL),
	('Term', 'TRM07', '12 Months', NULL, 7, NULL),
	('Term', 'TRM08', 'Other', NULL, 8, NULL),
	
	('Interest paid', 'IPD01', 'Up-front', NULL, 1, NULL),
	('Interest paid', 'IPD02', 'Maturity', NULL, 2, NULL),
	('Interest paid', 'IPD03', 'Monthly', NULL, 3, NULL),
	('Interest paid', 'IPD04', 'Quarterly', NULL, 4, NULL),
	
	('Term end', 'TRE01', 'EUR', NULL, 1, NULL),
	('Term end', 'TRE02', 'EUR', NULL, 2, NULL),
	('Term end', 'TRE03', 'EUR', NULL, 3, NULL);
	