create database msp;

create table msp_user_info (
  id int not null,
  name varchar(65) not null,
  gender int not null,
  age int not null,
  telphone varchar(11) not null,
  register_mode varchar(128) not null,
  third_id varchar(65) not null,
  constraint pk_user_info primary key(id),
  constraint uk_user_info unique key(id, telphone),
  constraint ck_user_telphone check(len(telphone) > 0 and len(telphone) <= 11)
) engine=InnoDB, default charset=utf8;

create table msp_user_password (
  id int not null,
  encrypt varchar(128) not null,
  user_id int not null,
  constraint pk_user_password primary key(id),
  constraint uk_user_password unique key(id, user_id),
  constraint fk_user_id foreign key(user_id) references msp_user_info(id) on delete cascade on update cascade
) engine=InnoDB, default charset=utf8;

--insert into msp_user_info values("", "", "", "", "", "", "");
insert into msp_user_info values("1", "A", "1", "23", "12345678901", "weixin", "2345");
insert into msp_user_info values("2", "B", "2", "23", "12345678902", "weixin", "2345");
insert into msp_user_info values("3", "C", "1", "24", "12345678903", "weixin", "2345");
insert into msp_user_info values("4", "D", "1", "20", "12345678904", "weixin", "2345");
insert into msp_user_info values("5", "E", "2", "21", "12345678905", "weixin", "2345");

drop table msp_user_info;

drop table msp_user_password;