drop SEQUENCE seq_rboard_no;
drop table rboard;

create sequence seq_rboard_no
increment by 1
start with 1
nocache;

create table rboard(
    no number,
    title varchar2(500),
    content varchar2(4000),
    hit number,
    reg_date date,
    user_no number not null,
    group_no number,
    order_no number,
    depth number,
    
    primary key(no),
    constraint fk_user_no foreign key(user_no) REFERENCES users(no)
);

insert into rboard
values(seq_rboard_no.nextval, 'title', 'contnet', 0, sysdate, 1, seq_rboard_no.currval, 1,  0);
insert into rboard
values(seq_rboard_no.nextval, 'title2', 'contnet2', 0, sysdate, 1, seq_rboard_no.currval, 1,  0);
insert into rboard
values(seq_rboard_no.nextval, 'title3', 'contnet3', 0, sysdate, 1, seq_rboard_no.currval, 1,  0);
insert into rboard
values(seq_rboard_no.nextval, 'title4', 'contnet4', 0, sysdate, 2, 2, 2,  1);
insert into rboard
values(seq_rboard_no.nextval, 'title4', 'contnet4', 0, sysdate, 1, 2, 3,  2);
commit;

select * from rboard;