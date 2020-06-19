CREATE TABLE public.movies
(
  id serial PRIMARY KEY NOT NULL,
  name varchar(100) NOT NULL,
  country varchar(50) NOT NULL,
  director varchar(50) NOT NULL,
  genre varchar(50) NOT NULL,
  age varchar(3) NOT NULL,
  ratingMPAA varchar(5) NOT NULL,
  time varchar(20) NOT NULL
);
CREATE UNIQUE INDEX movies_id_uindex ON public.movies (id);