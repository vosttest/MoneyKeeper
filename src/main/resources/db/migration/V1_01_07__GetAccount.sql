-- 1. Drop function
DROP FUNCTION IF EXISTS get_account();

-- 2. Create function
CREATE OR REPLACE FUNCTION get_account(keyword VARCHAR, uid INT4)
RETURNS TABLE(id INT4, code VARCHAR, text VARCHAR, balance FLOAT8, currency VARCHAR, rate FLOAT8) AS $body$
BEGIN
RETURN QUERY
	SELECT a.id, a.type, a.text, a.balance + COALESCE(b.total, 0) as balance, a.currency, c.rate
	FROM account a
	LEFT JOIN	(
					SELECT account_id, SUM(CASE WHEN type = 'Income' THEN total ELSE -total END) AS total
					FROM voucher
					WHERE user_id = uid
					GROUP BY account_id
				) b ON a.id = b.account_id
	LEFT JOIN	(
					SELECT p.value, (p.text::FLOAT8 /	(
															SELECT COALESCE(ch.text, '1')
															FROM common ch
															JOIN setting s ON s.text = 'Currency'
																AND ch.value = COALESCE(s.value, 'VND')
																AND s.user_id = uid
																WHERE ch.type = 'Rate'
														)::FLOAT8) AS rate
					FROM common p
					WHERE p.type = 'Rate'
				) c ON c.value = a.currency
	WHERE a.user_id = uid
	AND a.is_deleted = FALSE
	AND UPPER(a.text) LIKE UPPER(CONCAT('%', keyword, '%'));
END;
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000