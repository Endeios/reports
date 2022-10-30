-- Widgets
select distinct origin, "name"  from calculation_event ce ;

-- all Values (datapoints?) for widget (weatherStation, temperature)

select event_timestamp,value from calculation_event ce where origin = 'weatherStation' and "name" = 'temperature' ;