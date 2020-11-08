/*
牛客每天有很多人登录，请你统计一下牛客每个日期登录新用户个数，
有一个登录(login)记录表，简况如下:
mysql> select * from login;
id	user_id	client_id	date
1	2	    1	        2020-10-12
2	3	    2	        2020-10-12
3	1	    2	        2020-10-12
4	2	    2	        2020-10-13
5	1	    2	        2020-10-13
6	3	    1	        2020-10-14
7	4	    1	        2020-10-14
8	4	    1	        2020-10-15

第1行表示id为2的用户在2020-10-12使用了客户端id为1的设备登录了牛客网，因为是第1次登录，所以是新用户
。。。
第4行表示id为2的用户在2020-10-13使用了客户端id为2的设备登录了牛客网，因为是第2次登录，所以是老用户
。。
最后1行表示id为4的用户在2020-10-15使用了客户端id为1的设备登录了牛客网，因为是第2次登录，所以是老用户


请你写出一个sql语句查询每个日期登录新用户个数，并且查询结果按照日期升序排序，上面的例子查询结果如下:

date	    new
2020-10-12  3
2020-10-13	0
2020-10-14  1
2020-10-15  0

查询结果表明:
2020-10-12，有3个新用户(id为2，3，1)登录
2020-10-13，没有新用户登录
2020-10-14，有1个新用户(id为4)登录
2020-10-15，没有新用户登录
*/

/*select t1.date,(case when t2.num is null then 0 else t2.num end) new
from
(select distinct date from login order by date) t1
left join
(select l1.date,count(distinct l1.user_id) num from login l1 where l1.user_id
not in (select l2.user_id from login l2 where l2.user_id=l1.user_id and l2.date<l1.date) group by l1.date) t2
on t1.date=t2.date*/

select t1.date,ifnull(count(t2.user_id),0) new
from (select distinct l1.date from login l1) t1
left join
(select min(l2.date) date,l2.user_id
from login l2
group by l2.user_id) t2 on t1.date=t2.date
group by t1.date
order by t1.date  --mysql8.0不再支持group by的隐式或显示排序