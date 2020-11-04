/**
获取有奖金的员工相关信息。
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
create table emp_bonus(
emp_no int not null,
received datetime not null,
btype smallint not null);
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL, PRIMARY KEY (`emp_no`,`from_date`));
给出emp_no、first_name、last_name、奖金类型btype、对应的当前薪水情况salary以及奖金金额bonus。 bonus类型btype为1其奖金为薪水salary的10%，btype为2其奖金为薪水的20%，其他类型均为薪水的30%。 当前薪水表示to_date='9999-01-01'
输出格式:
emp_no	first_name	last_name	btype	salary	bonus
10001	Georgi	    Facello	    1	    88958	8895.8
10002	Bezalel	    Simmel	    2	    72527	14505.4
10003	Parto	    Bamford	    3	    43311	12993.3
10004	Chirstian	Koblick	    1	    74057	7405.7
 */
select em.emp_no,em.first_name,em.last_name,eb.btype,s.salary,
(case eb.btype
 when 1 then s.salary*0.1
 when 2 then s.salary*0.2
 else s.salary*0.3 end) as bonus
from employees em join emp_bonus eb on em.emp_no=eb.emp_no
join salaries s on em.emp_no=s.emp_no and s.to_date='9999-01-01'