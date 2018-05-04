DROP TABLE IF EXISTS PUBLIC."common";
CREATE TABLE PUBLIC."common"
(
	"id"					SERIAL PRIMARY KEY,
	"type"					VARCHAR(64),
	"value"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"sequence"				INT4,
	"parent_id"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."common" ("type", "value", "text", "description", "sequence", "parent_id") VALUES
	('Account', 'CSH', 'Cash', NULL, 1, NULL),
	('Account', 'BNK', 'Bank Account', NULL, 2, NULL),
	('Account', 'ATM', 'ATM', NULL, 3, NULL),
	('Account', 'DAT', 'Deposit Account', NULL, 4, NULL),
	('Account', 'SAT', 'Save Account', NULL, 5, NULL),
	('Account', 'OTH', 'Other', NULL, 6, NULL),
	
	('Setting', '20:00', 'Reminder', NULL, 5, NULL);
	
DROP TABLE IF EXISTS PUBLIC."account";
CREATE TABLE PUBLIC."account"
(
	"id"					SERIAL PRIMARY KEY,
	"value"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"sequence"				INT4,
	"parent_id"				INT4,
	"user_id"				INT4,
	"balance"				FLOAT8,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."account" ("value", "text", "description", "sequence", "parent_id", "user_id", "balance") VALUES
	('CSH', 'Cash', NULL, 1, NULL, 1, 0),
	('BNK', 'Bank Account', NULL, 2, NULL, 1, 0),
	('ATM', 'ATM', NULL, 3, NULL, 1, 0),
	('DAT', 'Deposit Account', NULL, 4, NULL, 1, 0),
	('SAT', 'Save Account', NULL, 5, NULL, 1, 0),
	('OTH', 'Other', NULL, 6, NULL, 1, 0),
	('CSH', 'Cash', NULL, 1, NULL, 2, 0),
	('BNK', 'Bank Account', NULL, 2, NULL, 2, 0),
	('ATM', 'ATM', NULL, 3, NULL, 2, 0),
	('DAT', 'Deposit Account', NULL, 4, NULL, 2, 0),
	('SAT', 'Save Account', NULL, 5, NULL, 2, 0),
	('OTH', 'Other', NULL, 6, NULL, 2, 0);

DROP TABLE IF EXISTS PUBLIC."setting";
CREATE TABLE PUBLIC."setting"
(
	"id"					SERIAL PRIMARY KEY,	
	"value"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"user_id"				INT4,	
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."setting" ("value", "text", "description", "user_id") VALUES
	('20:00', 'Reminder', NULL, 1),
	('19:00', 'Reminder', NULL, 2);