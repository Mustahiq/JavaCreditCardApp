Create External table IF NOT EXISTS cdw_sapp_customer( ssn INT, first_name VARCHAR(40), middle_name VARCHAR(40),
										last_name VARCHAR(40), credit_card_no STRING,
										cust_street VARCHAR(38), cust_city VARCHAR(30),
									    cust_country VARCHAR(30), cust_zip STRING,
										cust_phone VARCHAR(8), cust_email VARCHAR(40),
										last_updated STRING, cust_state VARCHAR(30))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
location "/user/cdw_sapp/cdw_sapp_customer";



SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
Create table IF NOT EXISTS partition_cdw_sapp_customer(ssn INT, first_name VARCHAR(40), middle_name VARCHAR(40),
										last_name VARCHAR(40), credit_card_no STRING,
										cust_street VARCHAR(38), cust_city VARCHAR(30),
									    cust_country VARCHAR(30), cust_zip STRING,
										cust_phone VARCHAR(8), cust_email VARCHAR(40),
										last_updated STRING)
Partitioned by (cust_state VARCHAR(8))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
INSERT OVERWRITE TABLE partition_cdw_sapp_customer PARTITION(cust_state)
SELECT ssn, first_name, middle_name, last_name, credit_card_no, cust_street, cust_city,
		cust_country, cust_zip, cust_phone, cust_email, last_updated, cust_state
from cdw_sapp_customer;
