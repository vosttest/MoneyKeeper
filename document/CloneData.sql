CREATE OR REPLACE FUNCTION cloneData(tableName VARCHAR)
RETURNS record AS $$
DECLARE
   rec record;
BEGIN
	IF tableName = 'Account' THEN
			INSERT INTO account(code, text) SELECT value, text FROM common WHERE type like tableName;
	ELSIF tableName = 'Expense' THEN
			INSERT INTO expense(code, text)	SELECT value, text FROM common WHERE type like tableName;
	ELSIF tableName = 'Income' THEN
			INSERT INTO income(code, text)	SELECT value, text FROM common WHERE type like tableName;
	END IF;
	RETURN rec;
END;
$$ LANGUAGE plpgsql;