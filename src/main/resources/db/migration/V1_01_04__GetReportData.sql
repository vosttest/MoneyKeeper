-- 1. Drop trigger and function
DROP FUNCTION IF EXISTS getReports();

-- 2. Create function
CREATE OR REPLACE FUNCTION getReports(accountId int ,fromDate TIMESTAMP, toDate TIMESTAMP)
RETURNS TABLE("type" varchar,"code" varchar, "text" varchar, "amount" FLOAT, "start_date" TIMESTAMP) AS $$
BEGIN
RETURN QUERY SELECT a.type, c.code, c.text, b.amount, a.start_date FROM voucher a
	JOIN voucher_detail b ON a.id = b.voucher_id
	JOIN expense c ON b.category = c.code
	WHERE a.type = 'Expense'
	AND a.account_id = accountId
	AND a.start_date BETWEEN fromDate AND toDate
	UNION ALL
	SELECT a.type, c.code, c.text, b.amount, a.start_date FROM voucher a
	JOIN voucher_detail b ON a.id = b.voucher_id
	JOIN income c ON b.category = c.code
	WHERE a.type = 'Income'
	AND a.account_id = accountId
	AND a.start_date BETWEEN fromDate AND toDate;
END;
	
$$
LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000