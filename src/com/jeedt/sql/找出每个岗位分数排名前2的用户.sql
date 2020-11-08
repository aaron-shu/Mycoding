/*
牛客每次举办企业笔试的时候，企业一般都会有不同的语言岗位，比如C++工程师，JAVA工程师，Python工程师，每个用户笔试完有不同的分数，现在有一个分数(grade)表简化如下:
mysql> select * from grade;
+----+-------------+-------+
| id | language_id | score |
+----+-------------+-------+
|  1 |           1 | 12000 |
|  2 |           1 | 13000 |
|  3 |           2 | 11000 |
|  4 |           2 | 10000 |
|  5 |           3 | 11000 |
|  6 |           1 | 11000 |
|  7 |           2 | 11000 |
+----+-------------+-------+
7 rows in set

第1行表示用户id为1的选择了language_id为1岗位的最后考试完的分数为12000，
....
第7行表示用户id为7的选择了language_id为2岗位的最后考试完的分数为11000，

不同的语言岗位(language)表简化如下:
mysql> select * from language;
+----+--------+
| id | name   |
+----+--------+
|  1 | C++    |
|  2 | JAVA   |
|  3 | Python |
+----+--------+
3 rows in set

请你找出每个岗位分数排名前2的用户，得到的结果先按照language的name升序排序，再按照积分降序排序，最后按照grade的id升序排序，得到结果如下:
+----+--------+-------+
| id | name   | score |
+----+--------+-------+
|  2 | C++    | 13000 |
|  1 | C++    | 12000 |
|  3 | JAVA   | 11000 |
|  7 | JAVA   | 11000 |
|  4 | JAVA   | 10000 |
|  5 | Python | 11000 |
+----+--------+-------+
 */

select g1.id,
(select l.name from language l where l.id=g1.language_id) name,
g1.score
from grade g1
where (select count(distinct g2.score) from grade g2 where g2.language_id=g1.language_id and g2.score>=g1.score)<=2
order by name,g1.score desc,g1.id