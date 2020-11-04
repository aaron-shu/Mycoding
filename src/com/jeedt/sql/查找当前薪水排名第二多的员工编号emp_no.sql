/**
查找当前薪水(to_date='9999-01-01')排名第二多的员工编号emp_no、薪水salary、last_name以及first_name，你可以不使用order by完成吗
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));

输出描述:
emp_no	salary	last_name	first_name
10009   94409   Peac        Sumant
 */

select e.emp_no,max(s.salary),e.last_name,e.first_name
from employees e inner join salaries s on e.emp_no=s.emp_no
where s.to_date='9999-01-01'
and s.salary<(select max(salary) from salaries where to_date='9999-01-01')