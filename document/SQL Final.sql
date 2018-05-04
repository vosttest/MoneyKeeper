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
	
	('Expense', 'EXP01', 'Lend', NULL, 1, NULL),
	('Expense', 'EXP02', 'Repayment', NULL, 2, NULL),
	('Expense', 'EXP03', 'Food And Dining', NULL, 3, NULL),	
	('Expense', 'EXP04', 'Utilities', NULL, 4, NULL),
	('Expense', 'EXP05', 'Auto & Transport', NULL, 5, NULL),
	('Expense', 'EXP06', 'Kids', NULL, 6, NULL),
	('Expense', 'EXP07', 'Clothing', NULL, 7, NULL),
	('Expense', 'EXP08', 'Gift & Donations', NULL, 8, NULL),	
	('Expense', 'EXP09', 'Health & Fitness', NULL, 9, NULL),
	('Expense', 'EXP10', 'Home', NULL, 10, NULL),
	('Expense', 'EXP11', 'Entertainment', NULL, 11, NULL),
	('Expense', 'EXP12', 'Personal', NULL, 12, NULL),
	('Expense', 'EXP13', 'Groceries', NULL, 13, 9),
	('Expense', 'EXP14', 'Restaurant', NULL, 14, 9),
	('Expense', 'EXP15', 'Bars & Coffee', NULL, 15, 9),
	('Expense', 'EXP16', 'Water', NULL, 16, 10),
	('Expense', 'EXP17', 'Electricity', NULL, 17, 10),
	('Expense', 'EXP18', 'Internet', NULL, 18, 10),
	('Expense', 'EXP19', 'Gas', NULL, 19, 10),
	('Expense', 'EXP20', 'Cable TV', NULL, 20, 10),
	('Expense', 'EXP21', 'Mobile Phone', NULL, 21, 10),
	('Expense', 'EXP22', 'Home Phone', NULL, 22, 10),
	('Expense', 'EXP23', 'Hiring Maid', NULL, 23, 10),
	('Expense', 'EXP24', 'Fuel', NULL, 24, 11),
	('Expense', 'EXP25', 'Service & Parts', NULL, 25, 11),
	('Expense', 'EXP26', 'Repair Vehicles', NULL, 26, 11),
	('Expense', 'EXP27', 'Parking', NULL, 27, 11),
	('Expense', 'EXP28', 'Car Wash', NULL, 28, 11),
	('Expense', 'EXP29', 'Taxi', NULL, 29, 11),
	('Expense', 'EXP30', 'Tuition', NULL, 30, 12),
	('Expense', 'EXP31', 'Books', NULL, 31, 12),
	('Expense', 'EXP32', 'Baby Supplies', NULL, 32, 12),	
	('Expense', 'EXP33', 'Toys', NULL, 33, 12),
	('Expense', 'EXP34', 'Allowance', NULL, 34, 12),
	('Expense', 'EXP35', 'Clothes', NULL, 35, 13),
	('Expense', 'EXP36', 'Shoes', NULL, 36, 13),
	('Expense', 'EXP37', 'Accessories', NULL, 37, 13),
	('Expense', 'EXP38', 'Marriages', NULL, 38, 14),
	('Expense', 'EXP39', 'Funerals', NULL, 39, 14),
	('Expense', 'EXP40', 'Charity', NULL, 40, 14),
	('Expense', 'EXP41', 'Gifts', NULL, 41, 14),
	('Expense', 'EXP42', 'Doctor', NULL, 42, 15),	
	('Expense', 'EXP43', 'Pharmacy', NULL, 43, 15),
	('Expense', 'EXP44', 'Sports', NULL, 44, 15),
	('Expense', 'EXP45', 'Furnishing', NULL, 45, 16),
	('Expense', 'EXP46', 'Repair Of Buildings', NULL, 46, 16),
	('Expense', 'EXP47', 'Home Services', NULL, 47, 16),
	('Expense', 'EXP48', 'Music', NULL, 48, 17),
	('Expense', 'EXP49', 'Travel', NULL, 49, 17),
	('Expense', 'EXP50', 'Make Up', NULL, 50, 17),
	('Expense', 'EXP51', 'Movies & DVDs', NULL, 51, 17),
	('Expense', 'EXP52', 'Cosmetic', NULL, 52, 17),	
	('Expense', 'EXP53', 'Education', NULL, 53, 18),
	('Expense', 'EXP54', 'Hobbies', NULL, 54, 18),
	
	('Income', 'INC01', 'Borrow', NULL, 1, NULL),
	('Income', 'INC02', 'Collecting Debts', NULL, 2, NULL),
	('Income', 'INC03', 'Salary', NULL, 3, NULL),	
	('Income', 'INC04', 'Bonus', NULL, 4, NULL),
	('Income', 'INC05', 'Awarded', NULL, 6, NULL),
	('Income', 'INC06', 'Interest', NULL, 7, NULL),
	('Income', 'INC07', 'Savings Interest', NULL, 6, NULL),
	
	('Setting', '20:00', 'Reminder', NULL, 1, NULL);

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
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."account" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
	('ACC01', 'Wallet', '', 1, NULL, 2),
	('ACC01', 'In Stock', '', 2, NULL, 3);

DROP TABLE IF EXISTS PUBLIC."setting";
CREATE TABLE PUBLIC."setting"
(
	"id"					SERIAL PRIMARY KEY,
	"code"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"user_id"				INT4,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."setting" ("code", "text", "description", "user_id") VALUES
	('20:00', 'Reminder', '', 2),
	('20:00', 'Reminder', '', 3);

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
('INC03', 'Salary', '', 1, NULL, 2),
('INC07', 'Savings Interest', '', 1, NULL, 2),
('INC05', 'Awarded', '', 1, NULL, 2),
('INC03', 'Salary', '', 2, NULL, 3);

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
('EXP03', 'Food And Dining', '', 1, NULL, 2),
('EXP04', 'Utilities', '', 2, NULL, 3);

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

INSERT INTO PUBLIC."voucher" ("serial", "account_id", "type", "total", "description", "object") VALUES
('155s85w5w1ds3a3', 1, 'Income', 10000, '', 'myself');

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

INSERT INTO PUBLIC."voucher_detail" ("voucher_id", "category", "amount") VALUES
(1, 'EXP03', 5000),
(1, 'EXP07', 2000),
(1, 'EXP05', 3000);

DROP TABLE IF EXISTS PUBLIC."role_func";
CREATE TABLE PUBLIC."role_func"
(
	"id"					SERIAL PRIMARY KEY,
	"role_id"				INT4,
	"func_id"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."function";
CREATE TABLE PUBLIC."function"
(
	"id"					SERIAL PRIMARY KEY,
	"parent_id"				INT4,
	"code"					VARCHAR(64),
	"display_as"			VARCHAR(64),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."role";
CREATE TABLE PUBLIC."role"
(
	"id"					SERIAL PRIMARY KEY,
	"name"					VARCHAR(64),
	"remark"				VARCHAR(64),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."role" ("name", "remark") VALUES
('Admin', 'Full'),
('User', 'Limit');

DROP TABLE IF EXISTS PUBLIC."users";
CREATE TABLE PUBLIC."users"
(
	"id" 					SERIAL PRIMARY KEY,
	"user_name"				VARCHAR(64),
	"password"				VARCHAR(64),
	"first_name"			VARCHAR(32),
	"last_name"				VARCHAR(32),
	"email"					VARCHAR(128),
	"contact_no"			VARCHAR(16),
	"remark"				VARCHAR(128),
	"status"				CHARACTER(3) DEFAULT 'ACT',
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."users" ("user_name", "password", "first_name", "last_name", "email", "contact_no", "remark") VALUES
('admin', 'admin', 't', 'nt', '1311517345b@gmail.com', '+841596321478', 'Admin'),
('u1', 'aa', 't', 'nt', '1311517345c@gmail.com', '+841593321478', 'User'),
('u2', 'aa', 't', 'nt', '1311517345d@gmail.com', '+841596521478', 'User');

DROP TABLE IF EXISTS PUBLIC."user_role";
CREATE TABLE PUBLIC."user_role"
(
	"id" 					SERIAL PRIMARY KEY,
	"user_id"				INT4,
	"role_id"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

INSERT INTO PUBLIC."user_role" ("user_id", "role_id") VALUES
(2, 2), (1, 1), (3, 2);

DROP TABLE IF EXISTS PUBLIC."log";
CREATE TABLE PUBLIC."log"
(
	"log_date" 				TIMESTAMP PRIMARY KEY,
	"action"				INT4,
	"old_data"				TEXT,
	"new_data"				TEXT,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."user_log";
CREATE TABLE PUBLIC."user_log"
(
	"log_date" 				TIMESTAMP PRIMARY KEY,
	"action"				INT4,
	"old_data"				TEXT,
	"new_data"				TEXT,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

ALTER TABLE PUBLIC."voucher_detail"
	ADD CONSTRAINT FK_voucher_detail_voucher FOREIGN KEY ("voucher_id") REFERENCES PUBLIC."voucher" ("id");

ALTER TABLE PUBLIC."income"
	ADD CONSTRAINT FK_income_users FOREIGN KEY ("user_id") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."expense"
	ADD CONSTRAINT FK_expense_users FOREIGN KEY ("user_id") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."setting"
	ADD CONSTRAINT FK_setting_users FOREIGN KEY ("user_id") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."account"
	ADD CONSTRAINT FK_account_users FOREIGN KEY ("user_id") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."voucher"
	ADD CONSTRAINT FK_voucher_account FOREIGN KEY ("account_id") REFERENCES PUBLIC."account" ("id");

ALTER TABLE PUBLIC."role_func"
	ADD CONSTRAINT FK_role_func_role FOREIGN KEY ("role_id") REFERENCES PUBLIC."role" ("id"),
	ADD CONSTRAINT FK_role_func_function FOREIGN KEY ("func_id") REFERENCES PUBLIC."function" ("id");

ALTER TABLE PUBLIC."user_role"
	ADD CONSTRAINT FK_user_role_users FOREIGN KEY ("user_id") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_user_role_role FOREIGN KEY ("role_id") REFERENCES PUBLIC."role" ("id");