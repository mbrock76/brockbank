DROP TABLE IF EXISTS project0."transactions" cascade;
DROP TABLE IF EXISTS project0."accounts" cascade;
DROP TABLE IF EXISTS project0."users" cascade;

CREATE TABLE project0."users" (
  "user_id" serial NOT NULL primary key,
  "username" text NOT NULL,
  "password" text NOT NULL,
  "checking_id" int DEFAULT NULL,
  "savings_id" int DEFAULT NULL,
  "credit_id" int DEFAULT NULL
);

CREATE TABLE project0."accounts" (
  "account_id" serial NOT NULL primary key,
  "user_id" int NOT null references project0."users"(user_id),
  "type" text NOT NULL,
  "balance" double precision NOT NULL DEFAULT 0
);

CREATE TABLE project0."transactions" (
  "transaction_id" serial NOT NULL primary key,
  "account_id" int NOT null references project0."accounts"(account_id),
  "amount" double precision NOT NULL,
  "description" varchar(25) NOT NULL,
  "date" date NOT NULL DEFAULT current_timestamp
);

INSERT INTO project0."users" ("username", "password", "checking_id", "savings_id", "credit_id") VALUES
('asdf', 'asdf', 1, 2, 3);

INSERT INTO project0."accounts" ("user_id", "type", "balance") VALUES
(1, 'checking', 1988.62),
(1, 'savings', 950),
(1, 'credit', 245.11);

INSERT INTO project0."transactions" ("account_id", "amount", "description", "date") VALUES
(1, 100, 'deposit', '2022-02-09'),
(1, 200, 'deposit', '2022-02-09'),
(1, 100, 'deposit', '2022-02-09'),
(1, 100, 'deposit', '2022-02-09'),
(1, 100, 'deposit', '2022-02-09'),
(1, 100, 'deposit', '2022-03-12'),
(1, 100, 'deposit', '2022-03-12'),
(1, 200, 'deposit', '2022-01-12'),
(1, 200, 'deposit', '2022-01-12'),
(1, 200, 'deposit', '2022-01-12'),
(1, 100.1, 'deposit', '2022-03-12'),
(1, 100.2, 'deposit', '2022-03-12'),
(1, 100.3, 'deposit', '2022-03-12'),
(1, -50, 'debit', '2022-03-12'),
(1, -50, 'debit', '2022-03-12'),
(1, -20.99, 'debit', '2022-03-12'),
(1, -10.99, 'debit', '2022-03-12'),
(1, -10, 'withdrawal', '2022-03-12'),
(1, -10, 'withdrawal', '2022-03-12'),
(1, -20, 'withdrawal', '2022-03-12'),
(1, -20, 'withdrawal', '2022-03-12'),
(1, -20, 'withdrawal', '2022-03-12'),
(2, 100, 'deposit', '2022-01-05'),
(2, 50, 'deposit', '2022-03-13'),
(2, 50, 'deposit', '2022-02-16'),
(2, 50, 'deposit', '2022-02-16'),
(2, 100, 'deposit', '2022-02-02'),
(2, 100, 'deposit', '2022-03-13'),
(2, 100, 'deposit', '2022-03-13'),
(2, 200, 'deposit', '2022-03-13'),
(2, 200, 'deposit', '2022-03-13'),
(2, 200, 'deposit', '2022-03-13'),
(2, 200, 'deposit', '2022-03-13'),
(2, -100, 'withdrawal', '2022-02-15'),
(2, -100, 'withdrawal', '2022-02-24'),
(2, -100, 'transfer', '2022-03-13'),
(2, -100, 'transfer', '2022-03-13'),
(3, -19.99, 'charge', '2022-03-13'),
(3, -19.99, 'charge', '2022-01-13'),
(3, -29.99, 'charge', '2022-02-10'),
(3, -29.99, 'charge', '2022-01-10'),
(3, -10, 'charge', '2022-02-18'),
(3, -10, 'charge', '2022-03-13'),
(3, -15, 'charge', '2022-02-09'),
(3, -15, 'charge', '2022-03-13'),
(3, -22.46, 'charge', '2022-01-14'),
(3, -22.16, 'charge', '2022-02-22'),
(3, -16.56, 'charge', '2022-03-13'),
(3, -25, 'charge', '2022-01-28'),
(3, -25, 'charge', '2022-01-13'),
(3, -25, 'charge', '2022-02-27'),
(3, -25, 'charge', '2022-02-11'),
(3, -25, 'charge', '2022-03-13'),
(3, -25, 'charge', '2022-03-13'),
(3, -25, 'charge', '2022-03-13'),
(3, 50, 'payment', '2022-01-28'),
(3, 50, 'payment', '2022-02-25'),
(3, 50, 'payment', '2022-03-13'),
(3, -2.55, 'interest', '2022-01-28'),
(3, -4.65, 'interest', '2022-02-25'),
(3, -1.77, 'interest', '2022-03-13');

