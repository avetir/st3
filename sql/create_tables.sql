CREATE TABLE H_user
(
    id CHAR(36) PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password INTEGER NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE H_room
(
    room_number INTEGER PRIMARY KEY NOT NULL,
    capacity INTEGER NOT NULL,
    room_class VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
	  price INTEGER NOT NULL
);

CREATE TABLE H_request
(
    id CHAR(36) PRIMARY KEY NOT NULL,
    status VARCHAR(20) NOT NULL,
    places_number INTEGER NOT NULL,
    date_time_in TIMESTAMP NOT NULL,
    date_time_out TIMESTAMP NOT NULL,
	  user_id CHAR(36) REFERENCES H_user (id),
	  room_number INTEGER REFERENCES H_room (room_number),
    room_class VARCHAR(50) NOT NULL
);

CREATE TABLE H_Bill
(
    id SERIAL PRIMARY KEY NOT NULL,
    status VARCHAR(20) NOT NULL,
    bill_date_time TIMESTAMP NOT NULL,
	  request_id SERIAL REFERENCES H_request (id)
);