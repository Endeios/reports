package io.endeios.reports.appLogic.queries;

import io.endeios.reports.domain.DataPoint;
import io.endeios.reports.domain.Widget;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WidgetRepository {
    private final JdbcTemplate jdbcTemplate;

    public WidgetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Widget> getAll() {
        return jdbcTemplate.query("select distinct origin, \"name\"  from calculation_event ce ;", rs -> {
            List<Widget> widgets = new ArrayList<>();
            while (rs.next()) {
                widgets.add(new Widget(rs.getString("origin"), rs.getString("name")));
            }
            return widgets;
        });
    }

    public List<Widget> getWidgetsOf(String origin) {
        return jdbcTemplate.query("select distinct origin, \"name\"  from calculation_event ce where origin = ?;", rs -> {
            List<Widget> widgets = new ArrayList<>();
            while (rs.next()) {
                widgets.add(new Widget(rs.getString("origin"), rs.getString("name")));
            }
            return widgets;
        }, origin );
    }

    public List<DataPoint> getWidgetData(String origin, String name) {
        Object[] params = new Object[]{origin, name};
        return jdbcTemplate.query("select event_timestamp,value from calculation_event ce where origin = ? and \"name\" = ? ",
                getPreparedStatementSetter(), params);
    }
    private static RowMapper<DataPoint> getPreparedStatementSetter() {
        return (rs, rowNum) -> new DataPoint(
                        Instant.ofEpochMilli(rs.getDate("event_timestamp").getTime()),
                        BigDecimal.valueOf(rs.getDouble("value"))
                );

    }
}
