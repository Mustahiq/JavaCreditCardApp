select round(sum(cc.transaction_value),2) as `Value of transactions`, cc.transaction_type as ` Transaction Type`, t.quarter as Quarter
from cdw_sapp_creditcard cc join cdw_sapp_time t on cc.transaction_id = t.transaction_id
group by cc.transaction_type, t.quarter;
