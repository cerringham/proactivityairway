CREATE TABLE IF NOT EXISTS company (
id SERIAL PRIMARY KEY,
name VARCHAR(150) NOT NULL,
share_capital FLOAT
);
CREATE TABLE IF NOT EXISTS site (
id SERIAL PRIMARY KEY,
company_id INT,
name VARCHAR(150) NOT NULL,
street VARCHAR(150) NOT NULL,
city VARCHAR(150) NOT NULL,
nation VARCHAR(3) NOT NULL,
head_quarter BOOLEAN NOT NULL,
phone_number VARCHAR(30) NOT NULL UNIQUE,
email VARCHAR(180) NOT NULL UNIQUE,
FOREIGN KEY (company_id) REFERENCES company(id)
);
CREATE TABLE IF NOT EXISTS task (
id SERIAL PRIMARY KEY,
name VARCHAR(150) NOT NULL
);
CREATE TABLE IF NOT EXISTS employee (
id SERIAL PRIMARY KEY,
name VARCHAR(150) NOT NULL,
surname VARCHAR(150) NOT NULL,
email VARCHAR(180) NOT NULL UNIQUE,
date_of_birth DATE NOT NULL,
ral FLOAT,
task_id INT,
FOREIGN KEY (task_id) REFERENCES task(id)
);
CREATE TABLE IF NOT EXISTS airport (
id SERIAL PRIMARY KEY,
name VARCHAR(150) NOT NULL,
international_code VARCHAR(5) NOT NULL UNIQUE,
street VARCHAR(150) NOT NULL,
city VARCHAR(150) NOT NULL,
nation VARCHAR(3) NOT NULL
);
CREATE TABLE IF NOT EXISTS customer (
id SERIAL PRIMARY KEY,
name VARCHAR(150) NOT NULL,
surname VARCHAR(150) NOT NULL,
street VARCHAR(150) NOT NULL,
city VARCHAR(150) NOT NULL,
nation VARCHAR(3) NOT NULL,
email VARCHAR(180) NOT NULL,
phone_number VARCHAR(30) NOT NULL,
gender VARCHAR(6) NOT NULL,
date_of_birthday DATE NOT NULL,
handicap BOOLEAN NOT NULL,
passport VARCHAR(100) NOT NULL UNIQUE,
identity_card VARCHAR(100) NOT NULL UNIQUE,
vaccinations VARCHAR(500) -- colera, febbre gialla, tifo, epatite a
);
CREATE TABLE IF NOT EXISTS fleet (
id SERIAL PRIMARY KEY,
airplane_description VARCHAR (150) NOT NULL,
number_of_seat INT NOT NULL,
availability  INT NOT NULL
);
CREATE TABLE IF NOT EXISTS route (
id SERIAL PRIMARY KEY,
departure INT NOT NULL,
arrival INT NOT NULL
);

CREATE TABLE IF NOT EXISTS flight(
id SERIAL PRIMARY KEY,
route_id INT,
fleet_id INT,
departure_time VARCHAR(20) NOT NULL,
arival_time VARCHAR(20) NOT NULL,
FOREIGN KEY (route_id) REFERENCES route(id),
FOREIGN KEY (fleet_id) REFERENCES fleet(id)
);

CREATE TABLE IF NOT EXISTS ticket (
id SERIAL PRIMARY KEY,
customer_id INT,
flight_id INT,
seat_code VARCHAR (5) NOT NULL,
FOREIGN KEY (flight_id) REFERENCES flight(id),
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

CREATE TABLE IF NOT EXISTS loyalty_program(
id serial primary key,
customer_id INT,
points INT,
FOREIGN KEY(customer_id) REFERENCES customer(id));

ALTER TABLE customer
ADD COLUMN loyalty_program_id INT;


ALTER TABLE customer
ADD CONSTRAINT fk_customer_loyalty_program FOREIGN KEY (loyalty_program_id) REFERENCES loyalty_program(id);

ALTER TABLE flight
RENAME COLUMN flight_date TO departure_date;

ALTER TABLE flight
ADD COLUMN arrival_date DATE;

ALTER TABLE flight
ALTER COLUMN arrival_time TYPE time USING arrival_time::time without time zone;

ALTER TABLE flight
ALTER COLUMN departure_time TYPE time USING departure_time::time without time zone;

ALTER TABLE flight
ADD COLUMN delay TIME without time zone;
