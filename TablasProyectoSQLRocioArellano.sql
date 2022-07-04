DROP SCHEMA IF EXISTS project; 
CREATE SCHEMA project; 
USE project ;

-- tablas usuarios --

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) ,
  password VARCHAR(255) ,
  username VARCHAR(255) ,
  PRIMARY KEY (id));
  
INSERT INTO user (name,password,username)
VALUES
("Merrill","$2a$10$RlE/RoYDD8oNH3jxqehtXu4RsBn4a53unqHc84NbMGSqwZVogLn0S","admin"),
("Ann","$2a$10$RlE/RoYDD8oNH3jxqehtXu4RsBn4a53unqHc84NbMGSqwZVogLn0S","accountholder");
  

  CREATE TABLE IF NOT EXISTS role (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  user_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user (id));
  
INSERT INTO role (name,user_id)
VALUES
("ADMIN", 1),
("ACCOUNTHOLDER", 2);
  

CREATE TABLE IF NOT EXISTS third_party (
  id INT NOT NULL AUTO_INCREMENT,
  hash_key VARCHAR(255),
  PRIMARY KEY (id));
  

CREATE TABLE account_holder (
  date_of_birth DATETIME(6) ,
  mailing_address INT ,
  city VARCHAR(255) ,
  country VARCHAR(255) ,
  direction VARCHAR(255) ,
  number INT ,
  id INT NOT NULL,
  PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES user (id));


INSERT INTO account_holder (date_of_birth, mailing_address, city, country, direction, number, id)
VALUES
  ("2023-07-01",64242,"Palma de Mallorca","Spain","calle salamanca",27,1),
  ("2022-05-04",25626,"Melilla","Spain","calle enamorados",21,2);


CREATE TABLE admin (
  id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES user (id));
  
INSERT INTO admin (id)
VALUES
  (1),
  (2);


-- tablas cuentas bancarias --


CREATE TABLE account (
  id INT NOT NULL AUTO_INCREMENT,
  balance_amount DECIMAL(19,2),
  balance_currency VARCHAR(255),
  creation_date DATETIME(6),
  penalty_fee_amount DECIMAL(19,2),
  penalty_fee_currency VARCHAR(255),
  secret_key VARCHAR(255),
  id_primary_owner INT NOT NULL,
  id_secondary_owner INT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_secondary_owner) REFERENCES account_holder (id),
  FOREIGN KEY (id_primary_owner) REFERENCES account_holder (id));

INSERT INTO account (balance_amount,balance_currency,creation_date,penalty_fee_amount,penalty_fee_currency,secret_key, id_primary_owner,id_secondary_owner )
VALUES
  (9420,"USD","2021-10-30",40,"USD","VZO42OGO0IT",1,2),
  (6478,"USD","2021-11-06",40,"USD","NCU66GDK8HQ",2,null);
  

CREATE TABLE checking (
  minimum_balance DECIMAL(19,2),
  minimum_currency VARCHAR(255),
  monthly_maintenance_fee_amount DECIMAL(19,2),
  monthly_maintenance_fee_currency VARCHAR(255),
  status_enum VARCHAR(255),
  id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES account (id));
INSERT INTO checking (minimum_balance,minimum_currency,monthly_maintenance_fee_amount,monthly_maintenance_fee_currency,status_enum, id)
VALUES
  (8054,"USD",12,"USD","ACTIVE",1),
  (6359,"USD",12,"USD","ACTIVE",2);

CREATE TABLE credit_card(
  credit_limit_amount DECIMAL(19,2),
  credit_limit_currency VARCHAR(255),
  credit_limit_max_amount DECIMAL(19,2),
  credit_limit_max_currency VARCHAR(255),
  interest_rate DECIMAL(19,2),
  interest_rate_min DECIMAL(19,2),
  id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES account (id));
  
INSERT INTO credit_card (credit_limit_amount,credit_limit_currency,credit_limit_max_amount,credit_limit_max_currency,interest_rate,interest_rate_min, id)
VALUES
  (100,"USD",100000,"USD",0.2,0.1,1),
  (100,"USD",100000,"USD",0.2,0.1,2);



CREATE TABLE savings (
  interest_rate DECIMAL(19,4),
  interest_rate_max DECIMAL(19,2),
  minimum_balance DECIMAL(19,2),
  minimum_currency VARCHAR(255),
  minimum_balance_min DECIMAL(19,2),
  minimum_currency_min VARCHAR(255),
  status_enum VARCHAR(255),
  id INT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id) REFERENCES account (id));
  
INSERT INTO savings (interest_rate,interest_rate_max,minimum_balance,minimum_currency,minimum_balance_min,minimum_currency_min,status_enum, id)
VALUES
  (0.0025,0.5,1000,"USD",100,"USD","ACTIVE",1),
  (0.0025,0.5,1000,"USD",100,"USD","ACTIVE",2);


CREATE TABLE student_checking(
  status_enum VARCHAR(255),
  id INT NOT NULL,
  PRIMARY KEY (id), 
  FOREIGN KEY (id) REFERENCES account (id));

INSERT INTO student_checking( status_enum,id)
VALUES
  ("ACTIVE",1),
  ("ACTIVE",2);
  
CREATE TABLE IF NOT EXISTS transfer_destino (
  account_receiver_id INT NOT NULL AUTO_INCREMENT,
  name_receiver VARCHAR(255),
  PRIMARY KEY (account_receiver_id));

  CREATE TABLE IF NOT EXISTS transferencia (
  amount_money DECIMAL(19,2) ,
  currency_money VARCHAR(255),
  sender_name VARCHAR(255),
  id INT NOT NULL,
  PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES account (id));

SELECT*FROM user;
SELECT*FROM role;
SELECT*FROM user WHERE username = 'admin';
SELECT*FROM user WHERE username = 'accountholder';

-- para los tests --

DROP SCHEMA IF EXISTS project_test; 
CREATE SCHEMA project_test; 
USE project_test ;

