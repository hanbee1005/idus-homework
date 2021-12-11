create table member
(
    email varchar(100) not null
        primary key,
    created_by varchar(100) not null,
    created_date datetime not null,
    gender varchar(1) null,
    name varchar(60) not null,
    nickname varchar(30) not null,
    password varchar(255) not null,
    phone_num varchar(20) not null
);
