INSERT INTO company(name, share_capital)
VALUES('Proactivity Airway', 65849.984);


INSERT INTO site(company_id, name, street, city, nation, head_quarter, phone_number, email)
VALUES(1, 'Sede Milano Malpensa', 'Malpensa Terminal 1', 'Milano', 'ITA', true, '038743221', 'milanomalpensa@gmail.com');
INSERT INTO site(company_id, name, street, city, nation, head_quarter, phone_number, email)
VALUES(1, 'Sede Milano', 'Corso garibaldi 45', 'Milano', 'ITA', false, '038747763', 'milanogaribaldi@gmail.com');

INSERT INTO customer(name, surname, street, city, nation, email, phone_number, gender, date_of_birthday, handicap, passport, identity_card, vaccinations)
VALUES('Veronica', 'Zuniga', 'via tortona 6', 'Milano', 'ITA', 'veronicazuniga@gmail.com', '3877685900', 'female', '2000-06-22', false, '0045795869', 'A648I89', null);
INSERT INTO customer(name, surname, street, city, nation, email, phone_number, gender, date_of_birthday, handicap, passport, identity_card, vaccinations)
VALUES('Dominick', 'Gonzales', 'via del carmine 21', 'Milano', 'ITA', 'domgonzales@gmail.com', '3677489758', 'male', '2001-09-04', false, '674872905', 'A6387O87', null);
INSERT INTO customer(name, surname, street, city, nation, email, phone_number, gender, date_of_birthday, handicap, passport, identity_card, vaccinations)
VALUES('Leticia ', 'Ross', 'corso mortara 09', 'Milano', 'ITA', 'leticiaross@gmail.com', '389965749', 'female', '2001-09-04', false, '7823925643', 'O6482W278', null);
INSERT INTO customer(name, surname, street, city, nation, email, phone_number, gender, date_of_birthday, handicap, passport, identity_card, vaccinations)
VALUES('Desiree ', 'Bates', 'corso garibaldi 23', 'Varese', 'ITA', 'desibates@gmail.com', '3900758642', 'other', '1994-02-02', false, '8467820753', 'P658307YY6', 'colera, febbre gialla');
INSERT INTO customer(name, surname, street, city, nation, email, phone_number, gender, date_of_birthday, handicap, passport, identity_card, vaccinations)
VALUES('Brad ', 'Lambert', 'corso regina 8', 'Milano', 'ITA', 'badlambert@gmail.com', '3677489758', 'male', '1997-09-20', false, '78937767', 'U7474975B', 'febbre gialla');


INSERT INTO task(name)
VALUES('Presidente');
INSERT INTO task(name)
VALUES('C.D.A');
INSERT INTO task(name)
VALUES('Facchino');
INSERT INTO task(name)
VALUES('Pilota');
INSERT INTO task(name)
VALUES('Hostess');
INSERT INTO task(name)
VALUES('Steward');
INSERT INTO task(name)
VALUES('impiegato');
INSERT INTO task(name)
VALUES('tecnico');
INSERT INTO task(name)
VALUES('operaio');


INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Milano Malpensa', 'MXP', '21010 Ferno VA', 'MILANO', 'ITA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('JFK', 'JFK', 'Queens, New York 11430', 'New York', 'USA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Miami International Airport', 'MIA', '2100 NW 42nd Ave, Miami, FL 33142', 'Miami', 'USA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Philadelphia International Airport', 'PHL', '8000 Essington Ave', 'Philadelphia', 'USA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Los Angeles International Airport', 'LAX', '1 World Way, Los Angeles, CA 90045', 'Los Angeles', 'USA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Logan Airport', 'BOS', 'Boston, Massachusetts 02128', 'Boston', 'USA');

INSERT INTO airport(name, international_code, street, city, nation)
VALUES('Tokio-Haneda Airport', 'HND', 'Hanedakuko, Ōta, Tokyo 144-0041', 'Tokio', 'JP');


 INSERT INTO route(departure, arrival)
 VALUES(1, 2);


 INSERT INTO route(departure, arrival)
 VALUES(1, 3);


 INSERT INTO route(departure, arrival)
 VALUES(1, 4);


 INSERT INTO route(departure, arrival)
 VALUES(2, 5);


 INSERT INTO route(departure, arrival)
 VALUES(3, 6);


 INSERT INTO route(departure, arrival)
 VALUES(2, 7);


 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Arlene', 'Ramirez', 'arleramirez@gmail.com', '1996-09-07', 2800, 4);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Jaime', 'Washington', 'jaimewash@gmail.com', '1985-10-23', 3800, 1);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Tricia', 'Miller', 'triciamiller@gmail.com', '1994-08-30', 2700, 2);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Iris', 'Martinez', 'irismartinez@gmail.com', '1987-05-04', 2700, 2);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Martin', 'Leonard', 'martinleo@gmail.com', '1980-07-07', 2700, 2);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Nora', 'Bass', 'norabass@gmail.com', '1986-09-09', 2800, 4);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Eddie', 'Stokes', 'eddiestokes@gmail.com', '2000-09-06', 2800, 4);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Clayton', 'Nunez', 'claytonnunez@gmail.com', '2001-01-01', 1800, 3);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Alfredo', 'Gardner', 'alfredogardner@gmail.com', '1999-04-22', 2500, 6);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Velma', 'Wilson', 'velmawillson@gmail.com', '1996-06-06', 2500, 5);
 INSERT INTO employee(name, surname, email, date_of_birth, ral, task_id)
 VALUES('Miriam', 'Gordon', 'miriamgordon@gmail.com', '1990-09-07', 2500, 5);

 INSERT INTO fleet(airplane_description, number_of_seat, availability)
 VALUES('Boeing 787', 400, 2);
 INSERT INTO fleet(airplane_description, number_of_seat, availability)
 VALUES('Boeing 777', 320, 2);


 INSERT INTO flight(route_id, fleet_id, departure_time, arrival_time)
 VALUES(2, 1, '17.09', '22.34');
 INSERT INTO flight(route_id, fleet_id, departure_time, arrival_time)
 VALUES(6, 1, '20.09', '15.04');
 INSERT INTO flight(route_id, fleet_id, departure_time, arrival_time)
 VALUES(7, 2, '09.38', '17.06');
 INSERT INTO flight(route_id, fleet_id, departure_time, arrival_time)
 VALUES(4, 2, '20.20', '17.03');
 INSERT INTO flight(route_id, fleet_id, departure_time, arrival_time)
 VALUES(5, 2, '07.09', '23.09');

 INSERT INTO fleet(airplane_description, number_of_seat, availability)
 VALUES('Boeing 787', 400, 2);
 INSERT INTO fleet(airplane_description, number_of_seat, availability)
 VALUES('Boeing 777', 320, 2);

 INSERT INTO flight(route_id, fleet_id, departure_time, arival_time, flight_date)
 VALUES(2, 1, '17.09', '22.34', '2023-06-20');
 INSERT INTO flight(route_id, fleet_id, departure_time, arival_time, flight_date)
 VALUES(6, 1, '20.09', '15.04', '2023-10-12');

 INSERT INTO flight(route_id, fleet_id, departure_time, arival_time, flight_date)
 VALUES(4, 2, '20.20', '17.03', '2023-04-21');
 INSERT INTO flight(route_id, fleet_id, departure_time, arival_time, flight_date)
 VALUES(5, 2, '07.09', '23.09', '2023-09-11');

 INSERT INTO flight(route_id, fleet_id, departure_time, arival_time, flight_date)
 VALUES(3, 2, '09.38', '17.06', '2023-01-12');



 INSERT INTO ticket(customer_id, flight_id, seat_code)
 VALUES(3, 5, '44a');

 INSERT INTO ticket(customer_id, flight_id, seat_code)
 VALUES(2, 4, '14a');

 INSERT INTO ticket(customer_id, flight_id, seat_code)
 VALUES(1, 4, '22c');

 INSERT INTO ticket(customer_id, flight_id, seat_code)
 VALUES(3, 1, '22c');

 INSERT INTO ticket(customer_id, flight_id, seat_code)
 VALUES(3, 4, '12b');