
CREATE OR REPLACE FUNCTION cloneData(tableName VARCHAR, id_User INTEGER)
RETURNS record AS $$
DECLARE
   rec record;
		rowid  INTEGER;
		ParentCode VARCHAR;
		curs1 CURSOR FOR SELECT value, text,parent_id
    FROM common WHERE type like 'Expense' and parent_id is NOT NULL;
BEGIN
		IF tableName = 'Account' THEN
			INSERT INTO account(code, text, user_id)
				SELECT value, text, id_User
				FROM common
				WHERE type like tableName;
		ELSIF tableName = 'Expense' THEN
			INSERT INTO expense(code, text, user_id)
				SELECT value, text, id_User
				FROM common
				WHERE type like 'Expense' AND parent_id is NULL;

				OPEN curs1;		
				
					LOOP
					-- fetch row into the film
					 FETCH curs1 INTO rec;
					-- exit when no more row to fetch
					 EXIT WHEN NOT FOUND;       
					-- build the output
					
					SELECT value into ParentCode
					fROM  common  
					where common."id"  = rec.parent_id; 
					
					SELECT id into rowid 
					FROM  expense 
					where expense.code = ParentCode AND expense.user_id = id_User;

					INSERT INTO expense(code, text, user_id, parent_id) 
					VALUES(rec.value, rec.text, id_User, rowid);
				
					END LOOP;
				 
			 CLOSE curs1;
				
		ELSIF tableName = 'Income' THEN
			INSERT INTO income(code, text, user_id)
				SELECT value, text, id_User 
				FROM common
				WHERE type like tableName;
					
		ELSIF tableName = 'Setting' THEN
			INSERT INTO setting(code, text, user_id)
				SELECT value, text, id_User 
				FROM common
				WHERE type like tableName;
		END IF;
		RETURN rec;
END;
$$ LANGUAGE plpgsql;



-- Test Data
SELECT cloneData('Account', 1)
SELECT cloneData('Expense', 1)
SELECT cloneData('Income', 1)
SELECT cloneData('Setting', 1)

-- Delete All Rows in Table
DELETE FROM expense;

-- Reset IDENTITY = 0 
TRUNCATE TABLE expense RESTART IDENTITY;


		
		
		