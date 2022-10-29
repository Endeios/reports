-- liquibase formatted sql
-- changeset create_calculation_event_table:1
CREATE TABLE calculation_event (
  id serial primary key,
  event_timestamp timestamp,
  origin  varchar(100),
  name varchar(100),
  value decimal
);


CREATE INDEX times_idx ON calculation_event(event_timestamp);
CREATE INDEX origin_idx ON calculation_event(origin);
CREATE INDEX name_idx ON calculation_event(name);

-- rollback: DROP TABLE calculation_event;