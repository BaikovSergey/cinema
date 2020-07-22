-- auto-generated definition
create table moviesessions
(
  id           serial      not null
    constraint halls_pkey
    primary key,
  data         date        not null,
  film_name    varchar(50) not null,
  hall_id      integer     not null,
  session_time varchar(50) not null,
  seats        text
);

alter table moviesessions
  owner to postgres;

