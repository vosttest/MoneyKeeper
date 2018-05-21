DROP TABLE IF EXISTS PUBLIC."token_authentication";
CREATE TABLE PUBLIC."token_authentication"
(
	"id" 					SERIAL PRIMARY KEY,
	"client_key"			VARCHAR(8),
	"module"				VARCHAR(32),
	"token"					VARCHAR(8),
	"token_expire_on"		TIMESTAMP,
	"user_id"				INT4,
	"is_verified"			TIMESTAMP
);