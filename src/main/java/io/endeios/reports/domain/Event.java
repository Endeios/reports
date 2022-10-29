package io.endeios.reports.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity(name = "calculation_event")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_timestamp")
    private Date event_times;

    @Column(name = "origin")
    private String origin;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private BigDecimal value;
}
