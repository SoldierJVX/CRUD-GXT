CREATE DATABASE login;

CREATE TABLE store (
  id SERIAL,
  description varchar(300) DEFAULT NULL,
  amount int(10) unsigned DEFAULT NULL,
  date_created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE user (
  id SERIAL,
  name varchar(150) DEFAULT NULL,
  login varchar(150) DEFAULT NULL,
  password varchar(150) DEFAULT NULL,
  PRIMARY KEY (id)
);
