CREATE TABLE public.accounts
(
  id serial PRIMARY KEY NOT NULL,
  login text NOT NULL,
  phoneNumber text NOT NULL
);
CREATE UNIQUE INDEX accounts_email_uindex ON public.accounts (phone_number);