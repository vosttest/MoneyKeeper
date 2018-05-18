-- Delete data
TRUNCATE TABLE PUBLIC."voucher" RESTART IDENTITY;
TRUNCATE TABLE PUBLIC."voucher_detail" RESTART IDENTITY;

-- Insert data
INSERT INTO PUBLIC."voucher" ("serial", "account_id", "type", "total", "description", "object", "user_id", "create_on") VALUES
	(NULL, 1, 'Expense', 20000, NULL, NULL, 1, '2018-05-18'),
	(NULL, 1, 'Income', 30000, NULL, NULL, 1, '2018-05-18');

INSERT INTO PUBLIC."voucher_detail" ("voucher_id", "category", "amount") VALUES
	(1, 'EXP003', 10000),
	(1, 'EXP004', 10000),
	(2, 'INC003', 30000);

SELECT c.code, c.text, b.amount FROM voucher a
JOIN voucher_detail b ON a.id = b.voucher_id
JOIN expense c ON b.category = c.code
WHERE a.type = 'Expense'
	AND a.account_id = 1
	AND a.create_on BETWEEN '2018-05-01' AND '2018-05-30';

SELECT c.code, c.text, b.amount FROM voucher a
JOIN voucher_detail b ON a.id = b.voucher_id
JOIN income c ON b.category = c.code
WHERE a.type = 'Income'
	AND a.account_id = 1
	AND a.create_on BETWEEN '2018-05-01' AND '2018-05-30';