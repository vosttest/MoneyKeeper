/*

INSERT INTO PUBLIC."account" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
('ACC01', 'Wallet', '', 1, NULL, 2),
('ACC01', 'In Stock', '', 2, NULL, 3);
INSERT INTO PUBLIC."setting" ("code", "text", "description", "user_id") VALUES
('20:00', 'Reminder', '', 2),
('20:00', 'Reminder', '', 3);
INSERT INTO PUBLIC."income" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
('INC03', 'Salary', '', 1, NULL, 2),
('INC07', 'Savings Interest', '', 1, NULL, 2),
('INC05', 'Awarded', '', 1, NULL, 2),
('INC03', 'Salary', '', 2, NULL, 3);
INSERT INTO PUBLIC."expense" ("code", "text", "description", "sequence", "parent_id", "user_id") VALUES
('EXP03', 'Food And Dining', '', 1, NULL, 2),
('EXP04', 'Utilities', '', 2, NULL, 3);
INSERT INTO PUBLIC."voucher" ("serial", "account_id", "type", "total", "description", "object") VALUES
('155s85w5w1ds3a3', 1, 'Income', 10000, '', 'myself');
INSERT INTO PUBLIC."voucher_detail" ("voucher_id", "category", "amount") VALUES
(1, 'EXP03', 5000),
(1, 'EXP07', 2000),
(1, 'EXP05', 3000);

*/

INSERT INTO PUBLIC."users" ("user_name", "password", "first_name", "last_name", "email", "contact_no", "remark") VALUES
('admin', 'admin', 't', 'nt', '1311517345b@gmail.com', '+841596321478', 'Admin');

INSERT INTO PUBLIC."role" ("name", "remark") VALUES
('Admin', 'Full'),
('User', 'Limit');

INSERT INTO PUBLIC."user_role" ("user_id", "role_id") VALUES
(1, 1);

INSERT INTO PUBLIC."common" ("type", "value", "text", "description", "sequence", "parent_id") VALUES
	('Account', 'ACC01', 'Cash', NULL, 1, NULL),
	('Account', 'ACC02', 'Bank Account', NULL, 2, NULL),
	('Account', 'ACC03', 'ATM', NULL, 3, NULL),
	('Account', 'ACC04', 'Deposit Account', NULL, 4, NULL),
	('Account', 'ACC05', 'Save Account', NULL, 5, NULL),
	('Account', 'ACC06', 'Other', NULL, 6, NULL),
	
	('Expense', 'EXP01', 'Lend', NULL, 1, NULL),
	('Expense', 'EXP02', 'Repayment', NULL, 2, NULL),
	('Expense', 'EXP03', 'Food And Dining', NULL, 3, NULL),	
	('Expense', 'EXP04', 'Utilities', NULL, 4, NULL),
	('Expense', 'EXP05', 'Auto & Transport', NULL, 5, NULL),
	('Expense', 'EXP06', 'Kids', NULL, 6, NULL),
	('Expense', 'EXP07', 'Clothing', NULL, 7, NULL),
	('Expense', 'EXP08', 'Gift & Donations', NULL, 8, NULL),	
	('Expense', 'EXP09', 'Health & Fitness', NULL, 9, NULL),
	('Expense', 'EXP10', 'Home', NULL, 10, NULL),
	('Expense', 'EXP11', 'Entertainment', NULL, 11, NULL),
	('Expense', 'EXP12', 'Personal', NULL, 12, NULL),
	('Expense', 'EXP13', 'Groceries', NULL, 13, 9),
	('Expense', 'EXP14', 'Restaurant', NULL, 14, 9),
	('Expense', 'EXP15', 'Bars & Coffee', NULL, 15, 9),
	('Expense', 'EXP16', 'Water', NULL, 16, 10),
	('Expense', 'EXP17', 'Electricity', NULL, 17, 10),
	('Expense', 'EXP18', 'Internet', NULL, 18, 10),
	('Expense', 'EXP19', 'Gas', NULL, 19, 10),
	('Expense', 'EXP20', 'Cable TV', NULL, 20, 10),
	('Expense', 'EXP21', 'Mobile Phone', NULL, 21, 10),
	('Expense', 'EXP22', 'Home Phone', NULL, 22, 10),
	('Expense', 'EXP23', 'Hiring Maid', NULL, 23, 10),
	('Expense', 'EXP24', 'Fuel', NULL, 24, 11),
	('Expense', 'EXP25', 'Service & Parts', NULL, 25, 11),
	('Expense', 'EXP26', 'Repair Vehicles', NULL, 26, 11),
	('Expense', 'EXP27', 'Parking', NULL, 27, 11),
	('Expense', 'EXP28', 'Car Wash', NULL, 28, 11),
	('Expense', 'EXP29', 'Taxi', NULL, 29, 11),
	('Expense', 'EXP30', 'Tuition', NULL, 30, 12),
	('Expense', 'EXP31', 'Books', NULL, 31, 12),
	('Expense', 'EXP32', 'Baby Supplies', NULL, 32, 12),	
	('Expense', 'EXP33', 'Toys', NULL, 33, 12),
	('Expense', 'EXP34', 'Allowance', NULL, 34, 12),
	('Expense', 'EXP35', 'Clothes', NULL, 35, 13),
	('Expense', 'EXP36', 'Shoes', NULL, 36, 13),
	('Expense', 'EXP37', 'Accessories', NULL, 37, 13),
	('Expense', 'EXP38', 'Marriages', NULL, 38, 14),
	('Expense', 'EXP39', 'Funerals', NULL, 39, 14),
	('Expense', 'EXP40', 'Charity', NULL, 40, 14),
	('Expense', 'EXP41', 'Gifts', NULL, 41, 14),
	('Expense', 'EXP42', 'Doctor', NULL, 42, 15),	
	('Expense', 'EXP43', 'Pharmacy', NULL, 43, 15),
	('Expense', 'EXP44', 'Sports', NULL, 44, 15),
	('Expense', 'EXP45', 'Furnishing', NULL, 45, 16),
	('Expense', 'EXP46', 'Repair Of Buildings', NULL, 46, 16),
	('Expense', 'EXP47', 'Home Services', NULL, 47, 16),
	('Expense', 'EXP48', 'Music', NULL, 48, 17),
	('Expense', 'EXP49', 'Travel', NULL, 49, 17),
	('Expense', 'EXP50', 'Make Up', NULL, 50, 17),
	('Expense', 'EXP51', 'Movies & DVDs', NULL, 51, 17),
	('Expense', 'EXP52', 'Cosmetic', NULL, 52, 17),	
	('Expense', 'EXP53', 'Education', NULL, 53, 18),
	('Expense', 'EXP54', 'Hobbies', NULL, 54, 18),
	
	('Income', 'INC01', 'Borrow', NULL, 1, NULL),
	('Income', 'INC02', 'Collecting Debts', NULL, 2, NULL),
	('Income', 'INC03', 'Salary', NULL, 3, NULL),	
	('Income', 'INC04', 'Bonus', NULL, 4, NULL),
	('Income', 'INC05', 'Awarded', NULL, 6, NULL),
	('Income', 'INC06', 'Interest', NULL, 7, NULL),
	('Income', 'INC07', 'Savings Interest', NULL, 6, NULL),
	
	('Setting', '20:00', 'Reminder', NULL, 1, NULL);