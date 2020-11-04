/**
给出每个员工每年薪水涨幅超过5000的员工编号emp_no、薪水变更开始日期from_date以及薪水涨幅值salary_growth，并按照salary_growth逆序排列。
提示：在sqlite中获取datetime时间对应的年份函数为strftime('%Y', to_date)
(数据保证每个员工的每条薪水记录to_date-from_date=1年，而且同一员工的下一条薪水记录from_data=上一条薪水记录的to_data)

CREATE TABLE `salaries` (
`emp_no` int(11) NOT NULL,
`salary` int(11) NOT NULL,
`from_date` date NOT NULL,
`to_date` date NOT NULL,
PRIMARY KEY (`emp_no`,`from_date`));
如：插入
INSERT INTO salaries VALUES(10001,52117,'1986-06-26','1987-06-26');
INSERT INTO salaries VALUES(10001,62102,'1987-06-26','1988-06-25');
INSERT INTO salaries VALUES(10002,72527,'1996-08-03','1997-08-03');
INSERT INTO salaries VALUES(10002,72527,'1997-08-03','1998-08-03');
INSERT INTO salaries VALUES(10002,72527,'1998-08-03','1999-08-03');
INSERT INTO salaries VALUES(10003,43616,'1996-12-02','1997-12-02');
INSERT INTO salaries VALUES(10003,43466,'1997-12-02','1998-12-02');

则输出：
emp_no  from_date   salary_growth
10001   1987-06-26  9985
 */

/*
select
s1.emp_no,
s1.from_date,
(select (s1.salary-s2.salary) from salaries s2
 where s2.emp_no=s1.emp_no
 and s2.to_date=s1.from_date) salary_growth
from salaries s1 where salary_growth>5000 order by salary_growth desc
*/
select
s1.emp_no,
s1.from_date,
(s1.salary-s2.salary) salary_growth
from salaries s1 join salaries s2
on s1.emp_no=s2.emp_no and s1.from_date=s2.to_date
where salary_growth>5000 order by salary_growth desc