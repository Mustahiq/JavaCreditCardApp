CREATE external table IF NOT EXISTS cdw_sapp_creditcard (transaction_id INT, timeId VARCHAR(8), cust_cc_no STRING,
										  cust_ssn INT, branch_code INT, transaction_value DOUBLE,
										  last_updated STRING, transaction_type VARCHAR(30))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
location "/user/cdw_sapp/cdw_sapp_creditcard";


SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
Create table IF NOT EXISTS partition_cdw_sapp_creditcard(transaction_id INT, timeId VARCHAR(8), cust_cc_no STRING,
										  cust_ssn INT, branch_code INT, transaction_value DOUBLE,
										  last_updated STRING)
Partitioned By (transaction_type VARCHAR(30))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';


SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
INSERT OVERWRITE TABLE partition_cdw_sapp_creditcard PARTITION(transaction_type)
select transaction_id, timeId, cust_cc_no, cust_ssn, branch_code, transaction_value, last_updated, transaction_type
from cdw_sapp_creditcard;
