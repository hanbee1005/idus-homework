create table orders
(
    order_id varchar(12) not null
        primary key,
    item_name varchar(100) not null,
    pay_dt datetime not null,
    member_email varchar(100) not null,
    created_by varchar(100) not null,
    created_date datetime not null,
    constraint orders_member_email_fk
        foreign key (member_email) references member (email)
);
