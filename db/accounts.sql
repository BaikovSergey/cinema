-- auto-generated definition
create table accounts
(
  id           serial not null
    constraint accounts_pkey
    primary key,
  user_name    text   not null,
  phone_number text   not null,
  sum          numeric
);

alter table accounts
  owner to postgres;