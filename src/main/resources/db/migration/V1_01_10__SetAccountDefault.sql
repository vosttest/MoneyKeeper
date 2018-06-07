-- 1. Drop function
DROP FUNCTION IF EXISTS set_account_default();

-- 2. Create function
CREATE OR REPLACE FUNCTION set_account_default(uid INT4)
RETURNS SETOF INTEGER AS $body$
DECLARE x VARCHAR;
BEGIN
	-- Get default currency in setting
	SELECT value INTO x
	FROM setting
	WHERE text = 'Currency' AND user_id = uid;
	
	-- Update currency of default account
	UPDATE account
	SET currency = x
	WHERE user_id = uid;
	
	RETURN;
END;
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000