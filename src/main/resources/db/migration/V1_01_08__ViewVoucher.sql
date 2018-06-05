-- 1. Drop function
DROP FUNCTION IF EXISTS view_voucher();

-- 2. Create function
CREATE OR REPLACE FUNCTION view_voucher(vid INT4, uid INT4)
RETURNS TABLE(id INT4, type VARCHAR, total FLOAT8, payee VARCHAR, payer VARCHAR, start_date TIMESTAMP, description VARCHAR, account VARCHAR, category TEXT) AS $body$
BEGIN
RETURN QUERY
	SELECT v.id, v.type, v.total, v.payee, v.payer, v.start_date, v.description, a.text AS account, b.text AS category
	FROM voucher v
	JOIN account a ON v.account_id = a.id
	JOIN	(
				SELECT voucher_id, array_to_string(ARRAY_AGG(c.text), ',') AS text
				FROM voucher_detail vd
				JOIN common c ON vd.category = c.value
				WHERE voucher_id = vid
				GROUP BY voucher_id
			) b ON v.id = b.voucher_id
	WHERE v.id = vid AND v.user_id = uid;
END;
$body$

LANGUAGE plpgsql VOLATILE
COST 100
ROWS 1000