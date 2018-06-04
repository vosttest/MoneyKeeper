-- 1. Drop function
DROP FUNCTION IF EXISTS get_account();

-- 2. Create function
CREATE OR REPLACE FUNCTION get_account(keyword VARCHAR, uid INT4)
RETURNS TABLE(id INT4, code VARCHAR, text VARCHAR, balance FLOAT8) AS $body$
BEGIN
RETURN QUERY
	SELECT a.id, a.type, a.text, a.balance + COALESCE(b.total, 0) as balance FROM account a
	LEFT JOIN	(
					SELECT account_id, SUM(CASE WHEN type = 'Income' THEN total ELSE -total END) as total
					FROM voucher
					WHERE user_id = uid
					GROUP BY account_id
				) b ON a.id = b.account_id
	WHERE a.user_id = uid
	AND a.is_deleted = FALSE
	AND UPPER(a.text) LIKE UPPER(CONCAT('%', keyword, '%'));
END;
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000