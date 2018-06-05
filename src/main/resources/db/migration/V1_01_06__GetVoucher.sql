-- 1. Drop function
DROP FUNCTION IF EXISTS get_voucher();

-- 2. Create function
CREATE OR REPLACE FUNCTION get_voucher(keyword VARCHAR, uid INT4, choose_date TIMESTAMP)
RETURNS TABLE(start_date TIMESTAMP, voucher_id INT4, account_id INT4, type VARCHAR,
	total FLOAT8, description VARCHAR, payee VARCHAR, payer VARCHAR, to_account INT4,
	user_id INT4, amount FLOAT8, category_text VARCHAR, icon VARCHAR, account_text VARCHAR) AS $body$
BEGIN
RETURN QUERY
	(SELECT DISTINCT v.start_date, v.id, v.account_id, v.type, v.total,
		v.description, v.payee, v.payer, v.to_account,
		v.user_id, vd.amount, c.text, c.icon, a.text
	FROM voucher v
	JOIN voucher_detail vd ON vd.voucher_id = v.id
	JOIN expense c ON vd.category = c.code
	JOIN account a ON a.id = v.account_id
	WHERE v.user_id = uid
		AND v.start_date >= choose_date
		AND (LOWER(c.Text) LIKE CONCAT('%', keyword, '%') 
			OR LOWER(v.description) LIKE CONCAT('%', keyword, '%')
			OR LOWER(v.type) LIKE CONCAT('%', keyword, '%'))
		AND v.is_deleted = FALSE
	ORDER BY v.start_date)
	UNION ALL
	(SELECT DISTINCT v.start_date, v.id, v.account_id, v.type, v.total,
		v.description, v.payee, v.payer, v.to_account,
		v.user_id, vd.amount, c.text, c.icon, a.text
	FROM voucher v
	JOIN voucher_detail vd ON vd.voucher_id = v.id
	JOIN income c ON vd.category = c.code
	JOIN account a ON a.id = v.account_id
	WHERE v.user_id = uid
		AND v.start_date >= choose_date
		AND (LOWER(c.Text) LIKE CONCAT('%', keyword, '%') 
			OR LOWER(v.description) LIKE CONCAT('%', keyword, '%')
			OR LOWER(v.type) LIKE CONCAT('%', keyword, '%'))
		AND v.is_deleted = FALSE
	ORDER BY v.start_date);
END;
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000