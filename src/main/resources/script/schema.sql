-- Create database register_login_db
create
database register_login_db;

-- Create table user_acc
create table user_acc
(
    id                     uuid         not null default gen_random_uuid() primary key,
    name                   varchar(100),
    email                  varchar(100) not null,
    password               text         not null,
    gender                 varchar(10),
    phone                  varchar(50),
    address                text,
    profile                text,
    is_account_non_locked  boolean               default false,
    is_account_non_expired boolean               default false,
    is_enabled             boolean               default false,
    created_at             timestamp             default current_timestamp,
    updated_at             timestamp,
    deleted_at             timestamp
);

-- Create table email_verification
create table email_verification
(
    id          uuid                          not null default gen_random_uuid() primary key,
    user_id     uuid references user_acc (id) not null,
    code        text                          not null,
    is_verified boolean                                default false,
    expire_at   date,
    created_at  timestamp                              default current_timestamp
);

-- Create table role
create table role
(
    id   uuid         not null default gen_random_uuid() primary key,
    name varchar(100) not null
);

-- Insert data to table role
insert into role (name)
VALUES ('ADMIN'),
       ('USER');