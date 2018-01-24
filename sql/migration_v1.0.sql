CREATE TYPE user_role AS ENUM ('USER', 'MANAGER');
ALTER TABLE H_user ALTER COLUMN role TYPE user_role USING role::user_role;

CREATE TYPE room_status AS ENUM ('EMPTY', 'BOOKED', 'TAKEN', 'UNAVAILABLE');
ALTER TABLE H_room ALTER COLUMN status TYPE room_status USING status::room_status;

CREATE TYPE room_class_type AS ENUM ('APLUS', 'A', 'B');
ALTER TABLE H_room ALTER COLUMN room_class TYPE room_class_type USING room_class::room_class_type;

CREATE TYPE room_class_type AS ENUM ('APLUS', 'A', 'B');
ALTER TABLE H_request ALTER COLUMN room_class TYPE room_class_type USING room_class::room_class_type;

CREATE TYPE request_status AS ENUM ('PENDING', 'FULFULLED', 'REJECTED');
ALTER TABLE H_request ALTER COLUMN status TYPE request_status USING status::request_status;