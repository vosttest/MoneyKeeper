DROP TABLE IF EXISTS PUBLIC."account";
CREATE TABLE PUBLIC."account"
(
	"id"					SERIAL PRIMARY KEY,
	"type"					VARCHAR(64),
	"text"					VARCHAR(128),
	"description"			VARCHAR(256),
	"balance"				FLOAT8,
	"currency"				VARCHAR(64),
	"bank"					VARCHAR(128),
	"start_date"			TIMESTAMP,
	"term"					VARCHAR(64),
	"interest_rate"			FLOAT8,
	"interest_rate_free"	FLOAT8,
	"interest_paid"			VARCHAR(64),
	"term_ended"			VARCHAR(64),
	"account_id"			INT4,
	"sequence"				INT4,
	"parent_id"				INT4,
	"user_id"				INT4,
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
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
	"is_deleted"			BOOLEAN NOT NULL DEFAULT FALSE,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);