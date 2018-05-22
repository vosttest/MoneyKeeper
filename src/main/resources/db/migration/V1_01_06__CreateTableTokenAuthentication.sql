DROP TABLE IF EXISTS PUBLIC."token_authentication";
CREATE TABLE PUBLIC."token_authentication"
(
	"id" 					SERIAL PRIMARY KEY,
	"client_key"			VARCHAR(64),
	"module"				VARCHAR(32),
	"token"					VARCHAR(8),
	"token_expire_on"		TIMESTAMP,
	"user_id"				INT4,
	"is_verified"			BOOLEAN,
	"create_by"				INT4,
	"create_on"				TIMESTAMP,
	"modify_by"				INT4,
	"modify_on"				TIMESTAMP
);