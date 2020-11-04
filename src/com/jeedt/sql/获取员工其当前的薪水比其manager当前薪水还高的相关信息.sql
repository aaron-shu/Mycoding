/**
获取员工其当前的薪水比其manager当前薪水还高的相关信息，当前表示to_date='9999-01-01',
结果第一列给出员工的emp_no，
第二列给出其manager的manager_no，
第三列给出该员工当前的薪水emp_salary,
第四列给该员工对应的manager当前的薪水manager_salary
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE `dept_manager` (
`dept_no` char(4) NOT NULL,
`emp_no` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));

输出描述:
emp_no	manager_no	emp_salary	manager_salary
10001   10002       88958       72527
10009   10010       95409       94409
 */

select t1.emp_no,t2.emp_no manager_no,t1.salary emp_salary,t2.salary manager_salary
from
(select de.emp_no,de.dept_no,s1.salary
from dept_emp de join salaries s1 on de.emp_no=s1.emp_no
where s1.to_date='9999-01-01') t1
join
(select dm.emp_no,dm.dept_no,s2.salary
from dept_manager dm join salaries s2 on dm.emp_no=s2.emp_no
where s2.to_date='9999-01-01') t2
on t1.dept_no=t2.dept_no
where t1.salary>t2.salary

