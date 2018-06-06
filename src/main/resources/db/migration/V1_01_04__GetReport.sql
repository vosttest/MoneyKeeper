-- 1. Drop function
DROP FUNCTION IF EXISTS get_report();

-- 2. Create function
CREATE OR REPLACE FUNCTION get_report(aid INT4, from_date TIMESTAMP, to_date TIMESTAMP)
RETURNS TABLE(type VARCHAR, code VARCHAR, text VARCHAR, amount FLOAT, start_date TIMESTAMP) AS $body$
BEGIN
RETURN QUERY
	SELECT DISTINCT a.type, c.code, c.text, b.amount, a.start_date FROM voucher a
	JOIN voucher_detail b ON a.id = b.voucher_id
	JOIN expense c ON b.category = c.code
	WHERE a.type = 'Expense'
		AND a.account_id = aid
		AND a.start_date BETWEEN from_date AND to_date
	UNION ALL
	SELECT DISTINCT a.type, c.code, c.text, b.amount, a.start_date FROM voucher a
	JOIN voucher_detail b ON a.id = b.voucher_id
	JOIN income c ON b.category = c.code
	WHERE a.type = 'Income'
		AND a.account_id = aid
		AND a.start_date BETWEEN from_date AND to_date;
END;	
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000