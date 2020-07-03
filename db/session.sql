CREATE TABLE public.movieSession
(
  id serial PRIMARY KEY NOT NULL,
  data date NOT NULL,
  film_name text NOT NULL,
  hall_id int NOT NULL,
  session_time varchar(50) NOT NULL,
  seats text  NOT NULL
);