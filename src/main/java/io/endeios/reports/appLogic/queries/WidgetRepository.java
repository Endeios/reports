package io.endeios.reports.appLogic.queries;

import io.endeios.reports.web.dto.Widget;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
