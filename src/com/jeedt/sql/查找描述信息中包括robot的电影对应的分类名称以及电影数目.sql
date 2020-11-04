/**
题目描述
film表
字段	说明
film_id	电影id
title	电影名称
description	电影描述信息
CREATE TABLE IF NOT EXISTS film (
film_id smallint(5)  NOT NULL DEFAULT '0',
title varchar(255) NOT NULL,
description text,
PRIMARY KEY (film_id));

category表
字段	说明
category_id	电影分类id
name	电影分类名称
last_update	电影分类最后更新时间
CREATE TABLE category  (
category_id  tinyint(3)  NOT NULL ,
name  varchar(25) NOT NULL, `last_update` timestamp,
PRIMARY KEY ( category_id ));

film_category表
字段	说明
film_id	电影id
category_id	电影分类id
last_update	电影id和分类id对应关系的最后更新时间
CREATE TABLE film_category  (
film_id  smallint(5)  NOT NULL,
category_id  tinyint(3)  NOT NULL, `last_update` timestamp);

查找描述信息(film.description)中包含robot的电影对应的分类名称(category.name)以及电影数目(count(film.film_id))，而且还需要该分类包含电影总数量(count(film_category.category_id))>=5部
如：输入为:
INSERT INTO film VALUES(1,'ACADEMY DINOSAUR','A Epic Drama of a Feminist And a Mad Scientist who must Battle a Teacher in The Canadian Rockies');
INSERT INTO film VALUES(2,'ACE GOLDFINGER','A Astounding Epistle of a Database Administrator And a Explorer who must Find a Car in Ancient China');
INSERT INTO film VALUES(3,'ADAPTATION HOLES','A Astounding Reflection of a Lumberjack And a Car who must Sink a Lumberjack in A Baloon Factory');
INSERT INTO film VALUES(4,'AFFAIR PREJUDICE','A Fanciful Documentary of a Frisbee And a Lumberjack who must Chase a Monkey in A Shark Tank');
INSERT INTO film VALUES(5,'AFRICAN EGG','A Fast-Paced Documentary of a Pastry Chef And a Dentist who must Pursue a Forensic Psychologist in The Gulf of Mexico');
INSERT INTO film VALUES(6,'AGENT TRUMAN','A Intrepid Panorama of a robot And a Boy who must Escape a Sumo Wrestler in Ancient China');
INSERT INTO film VALUES(7,'AIRPLANE SIERRA','A Touching Saga of a Hunter And a Butler who must Discover a Butler in A Jet Boat');
INSERT INTO film VALUES(8,'AIRPORT POLLOCK','A Epic Tale of a Moose And a Girl who must Confront a Monkey in Ancient India');
INSERT INTO film VALUES(9,'ALABAMA DEVIL','A Thoughtful Panorama of a Database Administrator And a Mad Scientist who must Outgun a Mad Scientist in A Jet Boat');
INSERT INTO film VALUES(10,'ALADDIN CALENDAR','A Action-Packed Tale of a Man And a Lumberjack who must Reach a Feminist in Ancient China');

INSERT INTO category VALUES(1,'Action','2006-02-14 20:46:27');
INSERT INTO category VALUES(2,'Animation','2006-02-14 20:46:27');
INSERT INTO category VALUES(3,'Children','2006-02-14 20:46:27');
INSERT INTO category VALUES(4,'Classics','2006-02-14 20:46:27');
INSERT INTO category VALUES(5,'Comedy','2006-02-14 20:46:27');
INSERT INTO category VALUES(6,'Documentary','2006-02-14 20:46:27');
INSERT INTO category VALUES(7,'Drama','2006-02-14 20:46:27');
INSERT INTO category VALUES(8,'Family','2006-02-14 20:46:27');
INSERT INTO category VALUES(9,'Foreign','2006-02-14 20:46:27');
INSERT INTO category VALUES(10,'Games','2006-02-14 20:46:27');
INSERT INTO category VALUES(11,'Horror','2006-02-14 20:46:27');
INSERT INTO category VALUES(12,'Music','2006-02-14 20:46:27');
INSERT INTO category VALUES(13,'New','2006-02-14 20:46:27');
INSERT INTO category VALUES(14,'Sci-Fi','2006-02-14 20:46:27');
INSERT INTO category VALUES(15,'Sports','2006-02-14 20:46:27');
INSERT INTO category VALUES(16,'Travel','2006-02-14 20:46:27');

INSERT INTO film_category VALUES(1,6,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(2,11,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(3,6,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(4,11,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(5,6,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(6,6,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(7,5,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(8,6,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(9,11,'2006-02-14 21:07:09');
INSERT INTO film_category VALUES(10,15,'2006-02-14 21:07:09');


输出为:
分类名称category.name   电影数目count(film.film_id)
Documentary             1
 */

select c.name,count(f.film_id)
from film f join film_category fc on f.film_id=fc.film_id
join category c on c.category_id=fc.category_id
where f.description like '%robot%'
and c.category_id in
(select t.category_id from film_category t
 group by t.category_id having count(t.category_id)>=5)
group by c.category_id