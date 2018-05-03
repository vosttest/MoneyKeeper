INSERT INTO PUBLIC."users" ("user_name", "password")
values ('a','a');

INSERT INTO PUBLIC."common_type" ("type_name", "create_by", "create_on", "modify_by", "modify_on")
VALUES('AccountType','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
('VoucherIncome','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
('VoucherExpense','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'Cash',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'Bank account',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'ATM',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'Deposit account',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'Save account',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(1,'Other',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 1),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Lend',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Repayment',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Food and Dining',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Utilities',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Auto & Transport',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Kids',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Clothing',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Gift & Donations',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Health & Fitness',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Home',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Entertainment',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Personal',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Groceries',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),9,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Restaurant',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),9,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Bars & Coffee',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),9,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Water',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Electricity',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Internet',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Gas',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Cable TV',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Mobile Phone',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Home Phone',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Hiring maid',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),10,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Fuel',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Service & Parts',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Repair vehicles',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Parking',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Car wash',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Taxi',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),11,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Tuition',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),12,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Books',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),12,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Baby Supplies',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),12,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Toys',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),12,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Allowance',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),12,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Clothes',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),13,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Shoes',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),13,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Accessories',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),13,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Marriages',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),14,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Funerals',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),14,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Charity',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),14,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Gifts',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),14,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Doctor',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),15,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Pharmacy',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),15,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Sports',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),15,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Furnishing',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),16,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Repair of buildings',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),16,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Home services',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),16,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Music',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),17,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Travel',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),17,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Make up',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),17,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Movies & DVDs',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),17,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Cosmetic',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),17,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Education',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),18,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(3,'Hobbies',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 3),18,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Borrow',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Collecting debts',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Salary',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Bonus',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Awarded',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Interest',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Other',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);
INSERT INTO PUBLIC."common_code" ("id_type", "value", "sequence", "id_parent", "create_by", "create_on", "modify_by", "modify_on")VALUES
(2,'Savings interest',(SELECT COUNT(*) + 1 FROM PUBLIC."common_code" WHERE "id_type" = 2),NULL,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."account" ("id_code", "name", "balance", "description", "create_by", "create_on", "modify_by", "modify_on")
VALUES
(1,'Wallet',10000,'','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."voucher" ("id_voucher", "id_account", "type", "total", "description", "object", "create_by", "create_on", "modify_by", "modify_on") VALUES
('kkask',1,'Income',500,'test record','myself','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
('kkask',1,'Expense',300,'test record','myself','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
('kkask',1,'Expense',200,'test record','myself','1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);

INSERT INTO PUBLIC."voucher_detail" ("id_master","id_code","amount","create_by","create_on","modify_by","modify_on") VALUES
(1,63,500,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
(2,9,300,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP),
(3,9,200,'1',CURRENT_TIMESTAMP,'1',CURRENT_TIMESTAMP);