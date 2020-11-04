/*
现在有一个需求，让你统计正常用户发送给正常用户邮件失败的概率:
有一个邮件(email)表，id为主键， type是枚举类型，枚举成员为(completed，no_completed)，completed代表邮件发送是成功的，no_completed代表邮件是发送失败的。简况如下:
mysql>select * from email;
id  send_id receive_id  type            date
1   2       3           completed       2020-01-11
2   1       3           completed       2020-01-11
3   1       4           no_completed    2020-01-11
4   3       1           completed       2020-01-12
5   3       4           completed       2020-01-12
6   4       1           completed       2020-01-12

第1行表示为id为2的用户在2020-01-11成功发送了一封邮件给了id为3的用户;
...
第3行表示为id为1的用户在2020-01-11没有成功发送一封邮件给了id为4的用户;
...
第6行表示为id为4的用户在2020-01-12成功发送了一封邮件给了id为1的用户;

下面是一个用户(user)表，id为主键，is_blacklist为0代表为正常用户，is_blacklist为1代表为黑名单用户，简况如下:
mysql> select * from user;
id  is_blacklist
1   0
2   1
3   0
4   0

第1行表示id为1的是正常用户;
第2行表示id为2的不是正常用户，是黑名单用户，如果发送大量邮件或者出现各种情况就会容易发送邮件失败的用户
。。。
第4行表示id为4的是正常用户

现在让你写一个sql查询，每一个日期里面，正常用户发送给正常用户邮件失败的概率是多少，结果保留到小数点后面3位(3位之后的四舍五入)，并且按照日期升序排序，上面例子查询结果如下:
date        p
2020-01-11  0.500
2020-01-12  0.000

结果表示:
2020-01-11失败的概率为0.500，因为email的第1条数据，发送的用户id为2是黑名单用户，所以不计入统计，正常用户发正常用户总共2次，但是失败了1次，所以概率是0.500;
2020-01-12没有失败的情况，所以概率为0.000.
 */
select e.date,
round(1.0*sum(case e.type when 'no_completed' then 1 else 0 end)/count(*) ,3) p
from email e join user u1 on e.send_id=u1.id
join user u2 on e.receive_id=u2.id
where u1.is_blacklist=0 and u2.is_blacklist=0
group by e.date