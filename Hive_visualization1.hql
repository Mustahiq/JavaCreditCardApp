select round(sum(cc.transaction_value),2) as Transaction_Value, b.branch_zip as Branch
from cdw_sapp_branch b join cdw_sapp_creditcard cc on cc.branch_code = b.branch_code
group by branch_zip
order by transaction_value DESC
limit 20;
