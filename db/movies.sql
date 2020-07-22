-- auto-generated definition
create table movies
(
  id         serial       not null
    constraint movies_pkey
    primary key,
  name       varchar(100) not null,
  country    varchar(50)  not null,
  director   varchar(50)  not null,
  genre      varchar(50)  not null,
  age        varchar(3)   not null,
  ratingmpaa varchar(5)   not null,
  time       varchar(20)  not null
);

alter table movies
  owner to postgres;

create unique index movies_id_uindex
  on movies (id);

