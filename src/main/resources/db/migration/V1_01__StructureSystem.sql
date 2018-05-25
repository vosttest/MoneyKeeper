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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"value"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"status"				CHARACTER(3) NOT NULL DEFAULT 'INA',
	"sequence"				INT4,
	"user_id"				INT4,
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
	"text"					VARCHAR(128),
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"remarks"				VARCHAR(128),
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."user";
CREATE TABLE PUBLIC."user"
(
	"id" 					SERIAL PRIMARY KEY,
	"user_name"				VARCHAR(64),
	"email"					VARCHAR(128),
	"account_no"			VARCHAR(64),
	"first_name"			VARCHAR(32),
	"last_name"				VARCHAR(32),
	"contact_no"			VARCHAR(16),
	"remarks"				VARCHAR(128),
	"status"				CHARACTER(3) NOT NULL DEFAULT 'ACT',
	"uuid"					UUID,
	"eoth"					UUID,
	"eoth_expiry_on"		TIMESTAMP,
	"is_email_verified"		BOOLEAN NOT NULL DEFAULT FALSE,
	"password_hash"			VARCHAR(256),
	"password_salt"			VARCHAR(256),
	"poth"					VARCHAR(256),
	"poth_expiry_on"		TIMESTAMP,
	"pass_reminder_token"	VARCHAR(256),
	"pass_reminder_expire"	TIMESTAMP,
	"activation_code"		VARCHAR(6),
	"activation_expire"		TIMESTAMP,
	"lock_expiry_on"		TIMESTAMP,
	"last_declaration_on"	TIMESTAMP,
	"last_login_on"			TIMESTAMP,
	"failed_auth_attempts"	INT4 NOT NULL DEFAULT 0,
	"is_locked"				BOOLEAN NOT NULL DEFAULT TRUE,
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."role_function";
CREATE TABLE PUBLIC."role_function"
(
	"id"					SERIAL PRIMARY KEY,
	"role_id"				INT4,
	"function_id"			INT4,
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);

DROP TABLE IF EXISTS PUBLIC."authentication";
CREATE TABLE PUBLIC."authentication"
(
	"id"					SERIAL PRIMARY KEY,
	"client_key"			VARCHAR(64),
	"module"				VARCHAR(32),
	"auth_key"				VARCHAR(8),
	"expire_on"				TIMESTAMP,
	"is_verified"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);