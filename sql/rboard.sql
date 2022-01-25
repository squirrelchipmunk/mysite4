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


set define off;
create or replace function depth_title( 
    title in varchar2, 
    depth in number
)return varchar2
    is
        str varchar2(1000);
        i number :=0;
    begin
        str := title;
        while(i<depth)
        loop
            str := concat('&nbsp;&nbsp;&nbsp;&nbsp;',str);
            i := i+1;
        end loop;        
    return str;
end;