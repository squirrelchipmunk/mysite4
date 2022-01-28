drop table users;
drop sequence SEQ_USERS_NO;

create sequence SEQ_USERS_NO
INCREMENT by 1
START with 1
nocache;

create table users(
    no number,
    id varchar2(20) not null unique,
    password varchar2(20) not null,
    name varchar2(20),
    gender varchar2(20),
    
    primary key(no)
);