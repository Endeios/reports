package io.endeios.reports.web.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class DataPoint {
    private Instant instant;
    private BigDecimal value;
}
