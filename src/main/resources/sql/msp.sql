create database msp;

drop table msp_user_password;
drop table msp_user_info;

create table msp_user_info (
  id int not null auto_increment,
  name varchar(65) not null,
  gender int not null,
  age int not null,
  telphone varchar(11) not null,
  register_mode varchar(128) not null,
  third_id varchar(65) not null,
  constraint pk_user_info primary key(id),
  constraint uk_user_info unique key(id),
  constraint ck_user_telphone check(len(telphone) > 0 and len(telphone) <= 11)
) engine=InnoDB, default charset=utf8, auto_increment=1;

create table msp_user_password (
  id int not null auto_increment,
  encrypt varchar(128) not null,
  user_id int not null,
  constraint pk_user_password primary key(id),
  constraint uk_user_password unique key(id),
  constraint fk_user_id foreign key(user_id) references msp_user_info(id) on delete cascade on update cascade
) engine=InnoDB, default charset=utf8, auto_increment=1;

alter table msp_user_info add constraint uk_user_info_telphone unique key(telphone);
alter table msp_user_password add constraint msp_user_password_user_id unique key(user_id);
alter table msp_user_info add constraint uk_user_info_name unique key(name);

--insert into msp_user_info values("", "", "", "", "", "", "");
insert into msp_user_info(name, gender, age, telphone, register_mode, third_id) values("A", "1", "23", "12345678901", "weixin", "2345");
insert into msp_user_info(name, gender, age, telphone, register_mode, third_id) values("B", "2", "23", "12345678902", "weixin", "2345");
-- insert into msp_user_info(name, gender, age, telphone, register_mode, third_id) values("C", "1", "24", "12345678903", "weixin", "2345");
-- insert into msp_user_info(name, gender, age, telphone, register_mode, third_id) values("D", "1", "20", "12345678904", "weixin", "2345");
-- insert into msp_user_info(name, gender, age, telphone, register_mode, third_id) values("E", "2", "21", "12345678905", "weixin", "2345");

insert into msp_user_password(encrypt, user_id) values("qweasdfasd", "1");
insert into msp_user_password(encrypt, user_id) values("qazwsxedcrfvtgb=", "2");

