drop table guestbook;
drop SEQUENCE seq_guestbook_no;

create table guestbook(
    no number,
    name varchar(80),
    password varchar2(20),
    content varchar2(2000),
    reg_date date,
    
    primary key(no)
);

create SEQUENCE seq_guestbook_no
increment by 1
start with 1
nocache;