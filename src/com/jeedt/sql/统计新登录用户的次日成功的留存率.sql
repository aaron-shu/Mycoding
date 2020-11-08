/*
牛客每天有很多人登录，请你统计一下牛客新登录用户的次日成功的留存率，
有一个登录(login)记录表，简况如下:
mysql> select * from login;
id  user_id client_id   date
1   2       1           2020-10-12
2   3       2           2020-10-12
3   1       2           2020-10-12
4   2       2           2020-10-13
5   4       1           2020-10-13
6   1       2           2020-10-13
7   1       2           2020-10-14

第1行表示id为2的用户在2020-10-12使用了客户端id为1的设备第一次新登录了牛客网
。。。
第4行表示id为3的用户在2020-10-12使用了客户端id为2的设备登录了牛客网
。。。
最后1行表示id为1的用户在2020-10-14使用了客户端id为2的设备登录了牛客网
 p
 0.500
查询结果表明:
id为1的用户在2020-10-12第一次新登录了，在2020-10-13又登录了，算是成功的留存
id为2的用户在2020-10-12第一次新登录了，在2020-10-13又登录了，算是成功的留存
id为3的用户在2020-10-12第一次新登录了，在2020-10-13没登录了，算是失败的留存
id为4的用户在2020-10-13第一次新登录了，在2020-10-14没登录了，算是失败的留存
固次日成功的留存率为 2/4=0.5
(sqlite里查找某一天的后一天的用法是:date(yyyy-mm-dd, '+1 day')，四舍五入的函数为round，sqlite 1/2得到的不是0.5，得到的是0，只有1*1.0/2才会得到0.5
mysql里查找某一天的后一天的用法是:DATE_ADD(min(date),INTERVAL 1 DAY)，四舍五入的函数为round)
 */

select
round(count(distinct l2.user_id)/(select count(distinct l1.user_id) from login l1) ,3) p
from login l2
where (l2.user_id,l2.date)
in (select l3.user_id,DATE_ADD(min(l3.date),INTERVAL 1 DAY) from login l3 group by l3.user_id );
