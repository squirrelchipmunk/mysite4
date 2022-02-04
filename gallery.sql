drop table gallery;
drop sequence seq_gallery_no;

create sequence seq_gallery_no
INCREMENT by 1
start with 1
nocache;

create table gallery(
    no number,
    user_no number,
    content varchar2(1000),
    filePath varchar2(500),
    orgName varchar2(200),
    saveName varchar2(500),
    fileSize number,
    
    PRIMARY KEY (no),
    constraint user_no foreign key (user_no) references users (no)
);