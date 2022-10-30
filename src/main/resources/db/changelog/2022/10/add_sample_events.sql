-- liquibase formatted sql
-- changeset add_sample_events:2

INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-10-29 12:00:00','weatherStation','temperature',19.9),
	 ('2022-10-30 12:00:00','weatherStation','temperature',20.1),
	 ('2022-10-31 12:00:00','weatherStation','temperature',20),
	 ('2022-11-01 12:00:00','weatherStation','temperature',19.9),
	 ('2022-11-02 12:00:00','weatherStation','temperature',20.1),
	 ('2022-11-03 12:00:00','weatherStation','temperature',20),
	 ('2022-11-04 12:00:00','weatherStation','temperature',20.9),
	 ('2022-11-05 12:00:00','weatherStation','temperature',21.1),
	 ('2022-11-06 12:00:00','weatherStation','temperature',21),
	 ('2022-11-07 12:00:00','weatherStation','temperature',20.9);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-08 12:00:00','weatherStation','temperature',21.1),
	 ('2022-11-09 12:00:00','weatherStation','temperature',21),
	 ('2022-11-10 12:00:00','weatherStation','temperature',21.9),
	 ('2022-11-11 12:00:00','weatherStation','temperature',22.1),
	 ('2022-11-12 12:00:00','weatherStation','temperature',22),
	 ('2022-11-13 12:00:00','weatherStation','temperature',21.9),
	 ('2022-11-14 12:00:00','weatherStation','temperature',22.1),
	 ('2022-11-15 12:00:00','weatherStation','temperature',22),
	 ('2022-11-16 12:00:00','weatherStation','temperature',22.9),
	 ('2022-11-17 12:00:00','weatherStation','temperature',23.1);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-18 12:00:00','weatherStation','temperature',23),
	 ('2022-11-19 12:00:00','weatherStation','temperature',22.9),
	 ('2022-11-20 12:00:00','weatherStation','temperature',23.1),
	 ('2022-11-21 12:00:00','weatherStation','temperature',23),
	 ('2022-11-22 12:00:00','weatherStation','temperature',23.9),
	 ('2022-11-23 12:00:00','weatherStation','temperature',24.1),
	 ('2022-11-24 12:00:00','weatherStation','temperature',24),
	 ('2022-11-25 12:00:00','weatherStation','temperature',23.9),
	 ('2022-11-26 12:00:00','weatherStation','temperature',24.1),
	 ('2022-11-27 12:00:00','weatherStation','temperature',24);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-28 12:00:00','weatherStation','temperature',24.9),
	 ('2022-11-29 12:00:00','weatherStation','temperature',25.1),
	 ('2022-11-30 12:00:00','weatherStation','temperature',25),
	 ('2022-12-01 12:00:00','weatherStation','temperature',24.9),
	 ('2022-12-02 12:00:00','weatherStation','temperature',25.1),
	 ('2022-12-03 12:00:00','weatherStation','temperature',25),
	 ('2022-12-04 12:00:00','weatherStation','temperature',25.9),
	 ('2022-12-05 12:00:00','weatherStation','temperature',26.1),
	 ('2022-10-29 12:00:00','weatherStation','humidity',55),
	 ('2022-10-30 12:00:00','weatherStation','humidity',60);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-10-31 12:00:00','weatherStation','humidity',45),
	 ('2022-11-01 12:00:00','weatherStation','humidity',50),
	 ('2022-11-02 12:00:00','weatherStation','humidity',55),
	 ('2022-11-03 12:00:00','weatherStation','humidity',60),
	 ('2022-11-04 12:00:00','weatherStation','humidity',45),
	 ('2022-11-05 12:00:00','weatherStation','humidity',50),
	 ('2022-11-06 12:00:00','weatherStation','humidity',55),
	 ('2022-11-07 12:00:00','weatherStation','humidity',60),
	 ('2022-11-08 12:00:00','weatherStation','humidity',45),
	 ('2022-11-09 12:00:00','weatherStation','humidity',50);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-10 12:00:00','weatherStation','humidity',55),
	 ('2022-11-11 12:00:00','weatherStation','humidity',60),
	 ('2022-11-12 12:00:00','weatherStation','humidity',45),
	 ('2022-11-13 12:00:00','weatherStation','humidity',50),
	 ('2022-11-14 12:00:00','weatherStation','humidity',55),
	 ('2022-11-15 12:00:00','weatherStation','humidity',60),
	 ('2022-11-16 12:00:00','weatherStation','humidity',45),
	 ('2022-11-17 12:00:00','weatherStation','humidity',50),
	 ('2022-11-18 12:00:00','weatherStation','humidity',55),
	 ('2022-11-19 12:00:00','weatherStation','humidity',60);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-20 12:00:00','weatherStation','humidity',45),
	 ('2022-11-21 12:00:00','weatherStation','humidity',50),
	 ('2022-11-22 12:00:00','weatherStation','humidity',55),
	 ('2022-11-23 12:00:00','weatherStation','humidity',60),
	 ('2022-11-24 12:00:00','weatherStation','humidity',45),
	 ('2022-11-25 12:00:00','weatherStation','humidity',50),
	 ('2022-11-26 12:00:00','weatherStation','humidity',55),
	 ('2022-11-27 12:00:00','weatherStation','humidity',60),
	 ('2022-11-28 12:00:00','weatherStation','humidity',45),
	 ('2022-11-29 12:00:00','weatherStation','humidity',50);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-30 12:00:00','weatherStation','humidity',56),
	 ('2022-12-01 12:00:00','weatherStation','humidity',61),
	 ('2022-12-02 12:00:00','weatherStation','humidity',46),
	 ('2022-12-03 12:00:00','weatherStation','humidity',51),
	 ('2022-12-04 12:00:00','weatherStation','humidity',56),
	 ('2022-12-05 12:00:00','weatherStation','humidity',61),
	 ('2022-10-29 12:00:00','weatherStation','hpa',994),
	 ('2022-10-30 12:00:00','weatherStation','hpa',995),
	 ('2022-10-31 12:00:00','weatherStation','hpa',996),
	 ('2022-11-01 12:00:00','weatherStation','hpa',995);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-02 12:00:00','weatherStation','hpa',994),
	 ('2022-11-03 12:00:00','weatherStation','hpa',993),
	 ('2022-11-04 12:00:00','weatherStation','hpa',994),
	 ('2022-11-05 12:00:00','weatherStation','hpa',995),
	 ('2022-11-06 12:00:00','weatherStation','hpa',996),
	 ('2022-11-07 12:00:00','weatherStation','hpa',995),
	 ('2022-11-08 12:00:00','weatherStation','hpa',994),
	 ('2022-11-09 12:00:00','weatherStation','hpa',993),
	 ('2022-11-10 12:00:00','weatherStation','hpa',995),
	 ('2022-11-11 12:00:00','weatherStation','hpa',996);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-12 12:00:00','weatherStation','hpa',997),
	 ('2022-11-13 12:00:00','weatherStation','hpa',996),
	 ('2022-11-14 12:00:00','weatherStation','hpa',995),
	 ('2022-11-15 12:00:00','weatherStation','hpa',994),
	 ('2022-11-16 12:00:00','weatherStation','hpa',995),
	 ('2022-11-17 12:00:00','weatherStation','hpa',996),
	 ('2022-11-18 12:00:00','weatherStation','hpa',997),
	 ('2022-11-19 12:00:00','weatherStation','hpa',996),
	 ('2022-11-20 12:00:00','weatherStation','hpa',995),
	 ('2022-11-21 12:00:00','weatherStation','hpa',994);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-11-22 12:00:00','weatherStation','hpa',996),
	 ('2022-11-23 12:00:00','weatherStation','hpa',997),
	 ('2022-11-24 12:00:00','weatherStation','hpa',998),
	 ('2022-11-25 12:00:00','weatherStation','hpa',997),
	 ('2022-11-26 12:00:00','weatherStation','hpa',996),
	 ('2022-11-27 12:00:00','weatherStation','hpa',995),
	 ('2022-11-28 12:00:00','weatherStation','hpa',996),
	 ('2022-11-29 12:00:00','weatherStation','hpa',997),
	 ('2022-11-30 12:00:00','weatherStation','hpa',998),
	 ('2022-12-01 12:00:00','weatherStation','hpa',997);
INSERT INTO public.calculation_event (event_timestamp,origin,"name",value) VALUES
	 ('2022-12-02 12:00:00','weatherStation','hpa',996),
	 ('2022-12-03 12:00:00','weatherStation','hpa',995),
	 ('2022-12-04 12:00:00','weatherStation','hpa',997),
	 ('2022-12-05 12:00:00','weatherStation','hpa',995);

-- rollback: Truncate TABLE calculation_event;