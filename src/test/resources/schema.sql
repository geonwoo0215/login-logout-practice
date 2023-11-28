drop table if exists member CASCADE;
create table member
(
    id bigint auto_increment primary key,
    login_id varchar(20),
    email varchar(20),
    password varchar(20),
    nick_name varchar(20)
)