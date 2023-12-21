-- Drop tables if they exist
DROP TABLE IF EXISTS `transactions`;
DROP TABLE IF EXISTS `accounts`;
DROP TABLE IF EXISTS `users`;

-- Create users table
CREATE TABLE `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` TEXT NOT NULL,
  `password` TEXT NOT NULL,
  `checking_id` INT DEFAULT NULL,
  `savings_id` INT DEFAULT NULL,
  `credit_id` INT DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

-- Create accounts table
CREATE TABLE `accounts` (
  `account_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `type` TEXT NOT NULL,
  `balance` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`account_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`user_id`)
);

-- Create transactions table
CREATE TABLE `transactions` (
  `transaction_id` INT NOT NULL AUTO_INCREMENT,
  `account_id` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `description` VARCHAR(25) NOT NULL,
  `date` DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`transaction_id`),
  FOREIGN KEY (`account_id`) REFERENCES `accounts`(`account_id`)
);

-- Insert data into users
INSERT INTO `users` (`username`, `password`, `checking_id`, `savings_id`, `credit_id`) VALUES
('asdf', 'asdf', 1, 2, 3);

-- Insert data into accounts
INSERT INTO `accounts` (`user_id`, `type`, `balance`) VALUES
(1, 'checking', 1988.62),
(1, 'savings', 950),
(1, 'credit', 245.11);

-- Insert data into transactions
INSERT INTO `transactions` (`account_id`, `amount`, `description`, `date`) VALUES
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
