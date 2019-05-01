CREATE EXTERNAL TABLE IF NOT EXISTS cdw_sapp_time (transaction_id INT, timeId VARCHAR(8), day INT, month INT, year INT, last_updated timestamp, quarter VARCHAR(8))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
location "/user/cdw_sapp/cdw_sapp_time";

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
Create table IF NOT EXISTS partition_cdw_sapp_time (transaction_id INT, timeId VARCHAR(8), day INT, month INT, year INT, last_updated timestamp)
Partitioned By (quarter VARCHAR(8))
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';


SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
INSERT OVERWRITE TABLE partition_cdw_sapp_time PARTITION(quarter)
Select transaction_id, timeId, day, month, year, last_updated, quarter
from cdw_sapp_time;
