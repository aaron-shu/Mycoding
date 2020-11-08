/*
牛客每天有很多人登录，请你统计一下牛客每个用户最近登录是哪一天，用的是什么设备.
有一个登录(login)记录表，简况如下:
mysql> select * from login;
id  user_id client_id   date
1   2       1           2020-10-12
2   3       2           2020-10-12
3   2       1           2020-10-13
4   3       2           2020-10-13
第1行表示id为2的用户在2020-10-12使用了客户端id为1的设备登录了牛客网
。。。
第4行表示id为3的用户在2020-10-13使用了客户端id为2的设备登录了牛客网

还有一个用户(user)表，简况如下:
mysql> select * from user;
id  name
1   tm
2   fh
3   wangchao

还有一个客户端(client)表，简况如下:
mysql> select * from client;
id  name
1   pc
2   ios
3   anroid
4   h5

请你写出一个sql语句查询每个用户最近一天登录的日子，用户的名字，以及用户用的设备的名字，并且查询结果按照user的name升序排序，上面的例子查询结果如下:
 u_n        c_n    d
 fh         pc     2020-10-13
 wangchao   ios    2020-10-13
查询结果表明:
fh最近的登录日期在2020-10-13，而且是使用pc登录的
wangchao最近的登录日期也是2020-10-13，而且是使用ios登录的
 */
select u.name u_n,c.name c_n,l.date d
from login l join user u on l.user_id=u.id
join client c on l.client_id=c.id
group by u.id having d=max(d)
order by  u_n