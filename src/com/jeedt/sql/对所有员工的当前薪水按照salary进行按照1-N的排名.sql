/**
对所有员工的当前(to_date='9999-01-01')薪水按照salary进行按照1-N的排名，相同salary并列且按照emp_no升序排列
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));

输出描述:
emp_no	salary	rank
10005   94692   1
10009   94409   2
10010   94409   2
10001   88958   3
10007   88070   4
10004   74057   5
10002   72527   6
10003   43311   7
10006   43311   7
10011   25828   8
 */

select
s1.emp_no,
s1.salary,
(select count(distinct s2.salary)
from salaries s2 where s2.to_date='9999-01-01' and s1.salary<=s2.salary) rank
from salaries s1 where s1.to_date='9999-01-01'
order by s1.salary desc,s1.emp_no
