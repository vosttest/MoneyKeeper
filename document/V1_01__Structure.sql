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
	('Account', 'ACC01', 'Cash', NULL, 1, NULL),
	('Account', 'ACC02', 'Bank Account', NULL, 2, NULL),
	('Account', 'ACC03', 'ATM', NULL, 3, NULL),
	('Account', 'ACC04', 'Deposit Account', NULL, 4, NULL),
	('Account', 'ACC05', 'Save Account', NULL, 5, NULL),
	('Account', 'ACC06', 'Other', NULL, 6, NULL),
	
	('Income', 'INC01', 'Borrow', NULL, 1, NULL),
	('Income', 'INC02', 'Collecting Debts', NULL, 2, NULL),
	
	('Expense', 'EXP01', 'Lend', NULL, 1, NULL),
	('Expense', 'EXP02', 'Repayment', NULL, 2, NULL),
	('Expense', 'EXP03', 'Food and Dining', NULL, 3, NULL),	
	('Expense', 'EXP04', 'Groceries', NULL, 4, 11),
	('Expense', 'EXP05', 'Restaurant', NULL, 6, 11),
	
	('Setting', '20:00', 'Reminder', NULL, 5, NULL);
	
DROP TABLE IF EXISTS PUBLIC."account";
CREATE TABLE PUBLIC."account"
(
	"id"					SERIAL PRIMARY KEY,
	"code"					VARCHAR(64),
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

INSERT INTO PUBLIC."account" ("code", "text", "description", "sequence", "parent_id", "user_id", "balance") VALUES
	('ACC01', 'Cash', NULL, 1, NULL, 1, 0),
	('ACC02', 'Bank Account', NULL, 2, NULL, 1, 0),
	('ACC03', 'ATM', NULL, 3, NULL, 1, 0),
	('ACC04', 'Deposit Account', NULL, 4, NULL, 1, 0),
	('ACC05', 'Save Account', NULL, 5, NULL, 1, 0),
	('ACC06', 'Other', NULL, 6, NULL, 1, 0),
	('ACC01', 'Cash', NULL, 1, NULL, 2, 0),
	('ACC02', 'Bank Account', NULL, 2, NULL, 2, 0),
	('ACC03', 'ATM', NULL, 3, NULL, 2, 0),
	('ACC04', 'Deposit Account', NULL, 4, NULL, 2, 0),
	('ACC05', 'Save Account', NULL, 5, NULL, 2, 0),
	('ACC06', 'Other', NULL, 6, NULL, 2, 0);

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
	
DROP TABLE IF EXISTS PUBLIC."income";
CREATE TABLE PUBLIC."income"
(
	"id"					SERIAL PRIMARY KEY,
	"code"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"sequence"				INT4,
	"parent_id"				INT4,
	"user_id"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."income" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
	('INC01', 'Borrow', NULL, 1, NULL, 1);

DROP TABLE IF EXISTS PUBLIC."expense";
CREATE TABLE PUBLIC."expense"
(
	"id"					SERIAL PRIMARY KEY,
	"code"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"sequence"				INT4,
	"parent_id"				INT4,
	"user_id"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."expense" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
	('EXP01', 'Lend', NULL, 1, NULL, 1),
	('EXP02', 'Repayment', NULL, 2, NULL, 1),
	('EXP03', 'Food and Dining', NULL, 3, NULL, 1),
	('EXP04', 'Groceries', NULL, 4, 3, 1),
	('EXP05', 'Restaurant', NULL, 6, 3, 1);
	
DROP TABLE IF EXISTS PUBLIC."voucher";
CREATE TABLE PUBLIC."voucher"
(
	"id" 					SERIAL PRIMARY KEY,
	"serial"				VARCHAR(32),
	"account_id"			INT4,
	"type"					VARCHAR(16),
	"total"					FLOAT8,
	"description"			VARCHAR(256),
	"object"				VARCHAR(64),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."voucher_detail";
CREATE TABLE PUBLIC."voucher_detail"
(
	"id" 					SERIAL PRIMARY KEY,
	"voucher_id"			INT4,
	"category"				VARCHAR(64), -- expense code OR income code
	"amount"				FLOAT8,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);