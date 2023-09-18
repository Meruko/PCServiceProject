create database pcservice;

use pcservice;

CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into role(name)
values ('USER'), ('ADMIN'), ('EMPLOYEE'), ('CRUDER');

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fio` varchar(200) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `role` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl5alypubd40lwejc45vl35wjb` (`role`),
  CONSTRAINT `FKl5alypubd40lwejc45vl35wjb` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into user(fio, active, password, role, username)
values ('admin', 1, '$2a$08$ml6naG/g5G/qUsIFmxos9.3iB3l8/V5IifiJg/CMyKqwi1/Fzh/fy', 2, 'admin'),
('cruder', 1, '$2a$08$MSr7K00SVLK/n61uXXkscuG.412nDXqPPpfMZDuRO.JjGgWZbebl2', 4, 'cruder');

CREATE TABLE `pctype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into pctype(name)
values ('ПК'), ('Ноутбук');

CREATE TABLE `pcmark` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into pcmark(name)
values ('Lenovo'), ('Acer'), ('HP'), ('MAC');

CREATE TABLE `ordertype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into ordertype(name)
values ('Ремонт'), ('Чистка');

CREATE TABLE `orderstatus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into orderstatus(name)
values ('В обработке'), ('Выполняется'), ('Выполнен'), ('Отменён');



select * from user;

-- delete from user_order where id = 1;
