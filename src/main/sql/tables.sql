CREATE DATABASE miniMall;
USE miniMall;
CREATE TABLE t_user (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(20),
  password VARCHAR(20),
  nickname VARCHAR(20),
  type     INT
);

CREATE TABLE t_address (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  adress   VARCHAR(200),
  phone    VARCHAR(20),
  postcode VARCHAR(20),
  user_id  INT,
  CONSTRAINT FOREIGN KEY (user_id)
  REFERENCES t_user (id)
);

ALTER TABLE t_good_in_cart_order
  CHANGE goo_price good_price INT;
CREATE TABLE t_order (
  id             INT PRIMARY KEY AUTO_INCREMENT,
  buy_date       DATETIME,
  pay_date       DATETIME,
  send_good_date DATETIME,
  confirm_date   DATETIME,
  price          DOUBLE,
  order_status   INT,
  user_id        INT,
  address_id     INT,
  CONSTRAINT FOREIGN KEY (user_id)
  REFERENCES t_user (id),
  CONSTRAINT FOREIGN KEY (address_id)
  REFERENCES t_address (id)
);


CREATE TABLE t_category (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20)
);

CREATE TABLE t_good (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  category_id INT,
  good_status INT,
  name        VARCHAR(20),
  price       DOUBLE,
  stock       INT,
  introduce   TEXT,
  img         VARCHAR(100),
  CONSTRAINT FOREIGN KEY (category_id)
  REFERENCES t_category (id)
);

CREATE TABLE t_good_in_cart_order (
  id          INT PRIMARY KEY AUTO_INCREMENT,
  good_id     INT,
  order_id    INT,
  good_number INT,
  good_price  INT,
  CONSTRAINT FOREIGN KEY (good_id)
  REFERENCES t_good (id),
  CONSTRAINT FOREIGN KEY (order_id)
  REFERENCES t_order (id)
);
DROP TABLE t_order;
SELECT *
FROM t_address;
INSERT INTO t_user (username, password, nickname, type) SELECT
                                                          username,
                                                          password,
                                                          nickname,
                                                          type
                                                        FROM t_user;
DELETE FROM t_user
WHERE id = 26;
INSERT INTO t_user (username, password, nickname, type) VALUES
  ('ran@qq.com', '123456', '潇然', 1),
  ('dream@qq.com', '123456', '梦', 1),
  ('leo@qq.com', '123456', 'leo', 1),
  ('leogun@qq.com', '123456', 'leogun', 1),
  ('cao@163.com', '123456', '曹操', 1),
  ('qinshaofu@qq.com', '123456', '章邯', 0),
  ('liu@163.com', '123456', '刘备', 0),
  ('HongXue@qq.com', '123456', '傅红雪', 0),
  ('lang@163.com', '123456', '沈浪', 0),
  ('feng@163.com', '123456', '萧乔峰', 0),
  ('daLi@qq.com', '123456', '段誉', 0),
  ('heshang@163.com', '123456', '虚竹', 0),
  ('jiangye@qq.com', '123456', '宁缺', 0),
  ('dongwu@qq.com', '123456', '孙权', 0),
  ('xiahou@qq.com', '123456', '夏侯惇', 0),
  ('caovipyuan@163.com', '123456', '夏侯渊', 0),
  ('guan@qq.com', '123456', '关羽', 0),
  ('yide@qq.com', '123456', '张飞', 0),
  ('kongming@163.com', '123456', '诸葛亮', 0),
  ('qingyunian@qq.com', '123456', '范闲', 0),
  ('qingmei@qq.com', '123456', '五竹', 1);

SELECT *
FROM t_good;
SELECT *
FROM t_notice;
DELETE FROM t_good WHERE id=30;

CREATE TABLE t_notice(
  id Int PRIMARY KEY AUTO_INCREMENT,
Title VARCHAR(10) not null,
Content TEXT not null,
Date DATETIME
);

select * from t_notice WHERE Title="free" order by id desc LIMIT 1;
