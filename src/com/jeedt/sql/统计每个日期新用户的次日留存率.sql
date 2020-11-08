/*
牛客每天有很多人登录，请你统计一下牛客每个日期新用户的次日留存率。
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

请你写出一个sql语句查询每个日期新用户的次日留存率，结果保留小数点后面3位数(3位之后的四舍五入)，并且查询结果按照日期升序排序，上面的例子查询结果如下:
date        p
2020-10-12  0.667
2020-10-13  0.000
2020-10-14  1.000
2020-10-15  0.000

查询结果表明:
2020-10-12登录了3个(id为2，3，1)新用户，2020-10-13，只有2个(id为2,1)登录，故2020-10-12新用户次日留存率为2/3=0.667;
2020-10-13没有新用户登录，输出0.000;
2020-10-14登录了1个(id为4)新用户，2020-10-15，id为4的用户登录，故2020-10-14新用户次日留存率为1/1=1.000;
2020-10-15没有新用户登录，输出0.000;
 */

 /*
 -- 每日新用户数
select t5.date,round(ifnull(t6.new*1.0/t5.new,0),3) p
from
(select t1.date,ifnull(count(t2.user_id),0) new
from (select distinct l1.date from login l1) t1
left join
(select min(l2.date) date,l2.user_id
from login l2
group by l2.user_id) t2 on t1.date=t2.date
group by t1.date) t5
left join
-- 新用户第二天登录数
(select date_sub(t3.date,interval 1 day) date,ifnull(count(t4.user_id),0) new
from (select distinct l3.date, l3.user_id from login l3) t3
left join
(select date_add(min(l4.date),interval 1 day) date,l4.user_id
from login l4
group by user_id) t4 on t3.date=t4.date and t3.user_id=t4.user_id
group by date) t6
on t5.date=t6.date
  */
select t1.date,round(count(t2.user_id) /count(t1.user_id), 3) p
from
(select user_id, min(date) date from login group by user_id) t1
left join (select distinct date,user_id from login) t2
on t1.user_id = t2.user_id
and t2.date = date_add(t1.date, interval 1 day)
group by t1.date
union
select date, 0.000 p from login
where date not in ( select min(date) from login group by user_id)
order by date;