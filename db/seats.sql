CREATE TABLE public.halls
(
  id serial PRIMARY KEY NOT NULL,
  seats TEXT NOT NULL
);
CREATE UNIQUE INDEX halls_id_uindex ON public.halls (id);