-- 1. Drop trigger and function
DROP TRIGGER IF EXISTS register ON public.user;
DROP FUNCTION IF EXISTS clone_data();

-- 2. Create function
CREATE FUNCTION clone_data()
RETURNS TRIGGER AS $body$
DECLARE
	rec record;
	uid INTEGER;
	rowid INTEGER;
	parent_code VARCHAR;
	curr_expense CURSOR FOR
		SELECT value, text, parent_id
		FROM common WHERE type LIKE 'Expense' AND parent_id IS NOT NULL;
	curr_income CURSOR FOR
		SELECT value, text, parent_id
		FROM common WHERE type LIKE 'Income' AND parent_id IS NOT NULL;
BEGIN
	uid := NEW.id; -- get user id

	-- Account ----------------------------
	INSERT INTO account(code, text, user_id)
	SELECT value, text, uid
	FROM common
	WHERE type LIKE 'Account';
	---------------------------------------

	-- Expense ----------------------------
	-- Step 1
	INSERT INTO expense(code, text, user_id)
	SELECT value, text, uid
	FROM common
	WHERE type LIKE 'Expense' AND parent_id IS NULL;
	-- Step 2
	OPEN curr_expense;
		LOOP
			FETCH curr_expense INTO rec;			
			EXIT WHEN NOT FOUND;			

			SELECT value into parent_code
			FROM common  
			WHERE common.id = rec.parent_id;

			SELECT id into rowid
			FROM expense
			WHERE expense.code = parent_code AND expense.user_id = uid;

			INSERT INTO expense(code, text, user_id, parent_id) 
			VALUES(rec.value, rec.text, uid, rowid);
		END LOOP;
	CLOSE curr_expense;
	---------------------------------------
				
	-- Income ----------------------------
	-- Step 1
	INSERT INTO income(code, text, user_id)
	SELECT value, text, uid
	FROM common
	WHERE type LIKE 'Income' AND parent_id IS NULL;
	-- Step 2
	OPEN curr_income;
		LOOP
			FETCH curr_income INTO rec;			
			EXIT WHEN NOT FOUND;			

			SELECT value into parent_code
			FROM common  
			WHERE common.id = rec.parent_id;

			SELECT id into rowid
			FROM income
			WHERE income.code = parent_code AND income.user_id = uid;

			INSERT INTO income(code, text, user_id, parent_id) 
			VALUES(rec.value, rec.text, uid, rowid);
		END LOOP;
	CLOSE curr_income;
	---------------------------------------
	
	-- Setting ----------------------------
	INSERT INTO setting(code, text, user_id)
	SELECT value, text, uid
	FROM common
	WHERE type LIKE 'Setting';
	---------------------------------------
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

-- 3. Create trigger
CREATE TRIGGER register
AFTER INSERT ON public.user
FOR EACH ROW EXECUTE PROCEDURE clone_data();

/* Delete data
DELETE FROM account;
DELETE FROM expense;
DELETE FROM income;
DELETE FROM setting;

TRUNCATE TABLE account RESTART IDENTITY;
TRUNCATE TABLE expense RESTART IDENTITY;
TRUNCATE TABLE income RESTART IDENTITY;
TRUNCATE TABLE setting RESTART IDENTITY;
*/