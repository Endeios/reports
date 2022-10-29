-- liquibase formatted sql
-- changeset create_calculation_event_table:1
CREATE TABLE calculation_event (
  event_timestamp timestamp,
  origin  varchar(100),
  name varchar(100),
  value decimal
);

-- rollback: DROP TABLE calculation_event;