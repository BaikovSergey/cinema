CREATE TABLE public.halls
(
  id serial PRIMARY KEY NOT NULL,
  data date NOT NULL,
  film_name text NOT NULL,
  hall_id int NOT NULL,
  seanse_time time NOT NULL,
  seat_1 boolean DEFAULT false  NOT NULL,
  seat_2 boolean DEFAULT false  NOT NULL,
  seat_3 boolean DEFAULT false  NOT NULL
);