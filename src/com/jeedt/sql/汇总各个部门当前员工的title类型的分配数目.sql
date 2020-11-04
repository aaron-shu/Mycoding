/**
汇总各个部门当前员工的title类型的分配数目，即结果给出部门编号dept_no、dept_name、其部门下所有的当前(dept_emp.to_date = '9999-01-01')员工的当前(titles.to_date = '9999-01-01')title以及该类型title对应的数目count
(注：因为员工可能有离职，所有dept_emp里面to_date不为'9999-01-01'就已经离职了，不计入统计，而且员工可能有晋升，所以如果titles.to_date 不为 '9999-01-01'，那么这个可能是员工之前的职位信息，也不计入统计)
CREATE TABLE `departments` (
`dept_no` char(4) NOT NULL,
`dept_name` varchar(40) NOT NULL,
PRIMARY KEY (`dept_no`));
CREATE TABLE `dept_emp` (
`emp_no` int(11) NOT NULL,
`dept_no` char(4) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`dept_no`));
CREATE TABLE IF NOT EXISTS `titles` (
`emp_no` int(11) NOT NULL,
`title` varchar(50) NOT NULL,
`from_date` date NOT NULL,
`to_date` date DEFAULT NULL);

输出描述:
dept_no	dept_name	        title           count
d001    Marketing           Senior Engineer 1
d001    Marketing           Staff           1
d002    Finance             Senior Engineer 1
d003    Human Resources     Senior Staff    1
d004    Production          Senior Engineer 2
d005    Development         Senior Staff    1
d006    Quality Management  Engineer        2
d006    Quality Management  Senior Engineer 1
 */

select d.dept_no,d.dept_name,t.title,count(t.title) 'count'
from departments d join dept_emp de on d.dept_no=de.dept_no
join titles t on de.emp_no=t.emp_no
where de.to_date='9999-01-01' and t.to_date='9999-01-01'
group by d.dept_no,t.title