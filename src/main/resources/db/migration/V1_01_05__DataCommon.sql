INSERT INTO PUBLIC."common" ("type", "value", "text", "description", "sequence", "parent_id") VALUES
	('Currency', 'VND', 'Việt Nam Đồng', NULL, 1, NULL),
	('Currency', 'USD', 'United States Dollar', NULL, 2, NULL),
	('Currency', 'EUR', 'Euro', NULL, 3, NULL),
	
	('Term', 'TRM01', '1 Week', NULL, 1, NULL),
	('Term', 'TRM02', '2 Weeks', NULL, 2, NULL),
	('Term', 'TRM03', '3 Weeks', NULL, 3, NULL),
	('Term', 'TRM04', '1 Month', NULL, 4, NULL),
	('Term', 'TRM05', '3 Months', NULL, 5, NULL),
	('Term', 'TRM06', '6 Months', NULL, 6, NULL),
	('Term', 'TRM07', '12 Months', NULL, 7, NULL),
	('Term', 'TRM08', 'Other', NULL, 8, NULL),
	
	('InterestPaid', 'IPD01', 'Up-front', NULL, 1, NULL),
	('InterestPaid', 'IPD02', 'Maturity', NULL, 2, NULL),
	('InterestPaid', 'IPD03', 'Monthly', NULL, 3, NULL),
	('InterestPaid', 'IPD04', 'Quarterly', NULL, 4, NULL),
	
	('TermEnd', 'TRE01', 'Rollover principal', NULL, 1, NULL),
	('TermEnd', 'TRE02', 'Rollover principal and interest', NULL, 2, NULL),
	('TermEnd', 'TRE03', 'Close account', NULL, 3, NULL);