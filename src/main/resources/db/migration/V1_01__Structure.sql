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
	"user_id"				INT4,
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