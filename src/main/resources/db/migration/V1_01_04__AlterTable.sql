ALTER TABLE PUBLIC."expense"
	ADD COLUMN "icon"					VARCHAR(100);
	
ALTER TABLE PUBLIC."income"
	ADD COLUMN "icon"					VARCHAR(100);
	
ALTER TABLE PUBLIC."account"
	ADD COLUMN "interest_rate_to"		INT4;