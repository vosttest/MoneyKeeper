DROP TABLE IF EXISTS PUBLIC."account";
CREATE TABLE PUBLIC."account"
(
	"id"							SERIAL PRIMARY KEY,
	"id_code"					INT4,
	"name"						VARCHAR(30),
	"balance"					FLOAT8,
	"description"			VARCHAR(100),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."common_code";
CREATE TABLE PUBLIC."common_code"
(
	"id"							SERIAL PRIMARY KEY,
	"id_type"					INT4,
	"value"						VARCHAR(100),
	"sequence"				INT4,
	"id_parent"				INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."common_type";
CREATE TABLE PUBLIC."common_type"
(
	"id"							SERIAL PRIMARY KEY,
	"type_name"				varchar(80),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."func_role";
CREATE TABLE PUBLIC."func_role"
(
	"id_role"					INT4,
	"id_func"					INT4,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP,
	PRIMARY KEY ("id_role", "id_func")
);

DROP TABLE IF EXISTS PUBLIC."function";
CREATE TABLE PUBLIC."function"
(
	"id"							SERIAL PRIMARY KEY,
	"func_name"				VARCHAR(30),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."role";
CREATE TABLE PUBLIC."role"
(
	"id"							SERIAL PRIMARY KEY,
	"func_name"				VARCHAR(30),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."log";
CREATE TABLE PUBLIC."log"
(
	"log_date" 				TIMESTAMP PRIMARY KEY,
	"action"					INT4,
	"old_data"				TEXT,
	"new_data"				TEXT,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."users";
CREATE TABLE PUBLIC."users"
(
	"id" 							SERIAL PRIMARY KEY,
	"user_name"				VARCHAR(60),
	"password"				VARCHAR(60),
	"id_role"					INT4,
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
	"action"					INT4,
	"old_data"				TEXT,
	"new_data"				TEXT,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."voucher";
CREATE TABLE PUBLIC."voucher"
(
	"id" 							SERIAL PRIMARY KEY,
	"id_voucher"			VARCHAR(32),
	"id_account"			INT4,
	"type"						VARCHAR(10),
	"total"						FLOAT8,
	"description"			VARCHAR(100),
	"object"					VARCHAR(30),
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."voucher_detail";
CREATE TABLE PUBLIC."voucher_detail"
(
	"id" 							SERIAL PRIMARY KEY,
	"id_master"				INT4,
	"id_code"					INT4,
	"amount"					FLOAT8,
	"is_deleted"			BOOLEAN DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

ALTER TABLE PUBLIC."account"
	ADD CONSTRAINT FK_account_common_code FOREIGN KEY ("id_code") REFERENCES PUBLIC."common_code" ("id"),
	ADD CONSTRAINT FK_account_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_account_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."common_code"
	ADD CONSTRAINT FK_common_code_common_type FOREIGN KEY ("id_type") REFERENCES PUBLIC."common_type" ("id"),
	ADD CONSTRAINT FK_common_code_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_common_code_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."common_type"
	ADD CONSTRAINT FK_common_type_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_common_type_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."log"
	ADD CONSTRAINT FK_log_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_log_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."user_log"
	ADD CONSTRAINT FK_user_log_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_user_log_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."voucher_detail"
	ADD CONSTRAINT FK_voucher_detail_common_code FOREIGN KEY ("id_code") REFERENCES PUBLIC."common_code" ("id"),
	ADD CONSTRAINT FK_voucher_detail_voucher FOREIGN KEY ("id_master") REFERENCES PUBLIC."voucher" ("id"),
	ADD CONSTRAINT FK_voucher_detail_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_voucher_detail_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."users"
	ADD CONSTRAINT FK_users_role FOREIGN KEY ("id_role") REFERENCES PUBLIC."role" ("id");

ALTER TABLE PUBLIC."voucher"
	ADD CONSTRAINT FK_voucher_account FOREIGN KEY ("id_account") REFERENCES PUBLIC."account" ("id"),
	ADD CONSTRAINT FK_voucher_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_voucher_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."role"
	ADD CONSTRAINT FK_role_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_role_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."function"
	ADD CONSTRAINT FK_function_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_function_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");

ALTER TABLE PUBLIC."func_role"
	ADD CONSTRAINT FK_func_role_role FOREIGN KEY ("id_role") REFERENCES PUBLIC."role" ("id"),
	ADD CONSTRAINT FK_func_role_function FOREIGN KEY ("id_func") REFERENCES PUBLIC."function" ("id"),
	ADD CONSTRAINT FK_func_role_users_create_by FOREIGN KEY ("create_by") REFERENCES PUBLIC."users" ("id"),
	ADD CONSTRAINT FK_func_role_users_modify_by FOREIGN KEY ("modify_by") REFERENCES PUBLIC."users" ("id");