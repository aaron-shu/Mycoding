/*
牛客每次考试完，都会有一个成绩表(grade)，如下:
mysql> select * from grade;
+----+------+-------+
| id | job  | score |
+----+------+-------+
|  1 | C++  | 11001 |
|  2 | C++  | 10000 |
|  3 | C++  |  9000 |
|  4 | Java | 12000 |
|  5 | Java | 13000 |
|  6 | 前端 | 12000 |
|  7 | 前端 | 11000 |
|  8 | 前端 |  9999 |
+----+------+-------+

第1行表示用户id为1的用户选择了C++岗位并且考了11001分
。。。
第8行表示用户id为8的用户选择了前端岗位并且考了9999分
请你写一个sql语句查询各个岗位分数的中位数位置上的所有grade信息，并且按id升序排序，结果如下:
+----+------+-------+--------+
| id | job  | score | t_rank |
+----+------+-------+--------+
|  2 | C++  | 10000 |      2 |
|  4 | Java | 12000 |      2 |
|  5 | Java | 13000 |      1 |
|  7 | 前端 | 11000 |      2 |
+----+------+-------+--------+
解释：
第1行表示C++岗位的中位数位置上的为用户id为2，分数为10000，在C++岗位里面排名是第2
第2，3行表示Java岗位的中位数位置上的为用户id为4,5，分数为12000,13000，在Java岗位里面排名是第2,1
第4行表示前端岗位的中位数位置上的为用户id为7，分数为11000，在前端岗位里面排名是第2
 */

select t1.*
from
(select
g1.*,
(select count(distinct g2.score) from grade g2 where g2.job=g1.job and g2.score>=g1.score) 'rank'
from grade g1) t1
join
(select job,
(case when count(score)%2=0 then count(score) div 2 else count(score) div 2+1 end) 'start',
count(score) div 2+1 'end'
from grade
group by job) t2
on t1.job=t2.job
where t1.rank between t2.start and t2.end
order by t1.id
