drop table guest02;
drop table user02;
drop sequence user02_seq;
drop sequence guest02_seq;

create table user02(
	num number primary key,
	id varchar2(30),
	pw varchar2(30),
	name varchar2(30)
);

create table guest02(
	num number primary key,
	sub varchar2(30),
	unum number,
	nalja date,
	pay number,
	foreign key(unum) references user02(num)
);
create sequence guest02_seq;
create sequence user02_seq;

--dummy
insert into user02 values(user02_seq.nextval,'master','1234','包府磊');
insert into user02 values(user02_seq.nextval,'user01','1234','蜡历1');
insert into user02 values(user02_seq.nextval,'user02','1234','蜡历2');

insert into guest02 values(guest02_seq.nextval,'test01',1,sysdate,1111);
insert into guest02 values(guest02_seq.nextval,'test02',1,sysdate,2222);
insert into guest02 values(guest02_seq.nextval,'test03',1,sysdate,3333);
insert into guest02 values(guest02_seq.nextval,'test04',1,sysdate,4444);
commit;

select * from user02;
select a.num,a.sub,a.unum,a.pay,b.name from guest02 A, user02 B where a.unum=b.num;
select num,sub,(select name from user02 where num=unum) as uname,pay from guest02;
