Create external table IF NOT EXISTS cdw_sapp_branch(branch_code INT, branch_name VARCHAR(25), branch_street VARCHAR(30),
									  branch_city VARCHAR(30), branch_zip STRING, branch_phone VARCHAR(30),
									  last_updated STRING, branch_state VARCHAR(30))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
location "/user/cdw_sapp/cdw_sapp_branch";



SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
Create table IF NOT EXISTS partition_cdw_sapp_branch(branch_code INT, branch_name VARCHAR(25), branch_street VARCHAR(30),
									  branch_city VARCHAR(30), branch_zip STRING, branch_phone VARCHAR(30),
									  last_updated STRING)
PARTITIONED BY (branch_state VARCHAR(30))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';



SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
INSERT OVERWRITE TABLE partition_cdw_sapp_branch PARTITION(branch_state)
SELECT branch_code, branch_name, branch_street, branch_city, branch_zip, branch_phone,
	   last_updated, branch_state
from cdw_sapp_branch;
