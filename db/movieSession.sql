-- auto-generated definition
create table moviesessions
(
  id           serial      not null
    constraint halls_pkey
    primary key,
  data         varchar(20) not null,
  film_name    varchar(50) not null,
  hall_id      integer     not null,
  session_time varchar(50) not null,
  seats        text        not null
);

alter table moviesessions
  owner to postgres;

create unique index moviesessions_seats_uindex
  on moviesessions (seats);