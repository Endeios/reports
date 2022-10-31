package io.endeios.reports.appLogic.queries;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.endeios.reports.domain.DataPoint;
import io.endeios.reports.domain.Widget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WidgetRepositoryTest {

    private HikariDataSource dataSource;
    private JdbcTemplate template;
    private WidgetRepository repo;

    @BeforeEach
    void setUp() {
        HikariConfig configuration = new HikariConfig();
        configuration.setUsername("postgres");
        configuration.setPassword("postgres");
        configuration.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource = new HikariDataSource(configuration);
        template = new JdbcTemplate(dataSource);
        repo = new WidgetRepository(template);
    }

    @Test
    void getTheCorrectWidgets() {
        List<Widget> widgets = repo.getAll();
        assertThat(widgets.size()).isEqualTo(3);
        assertWidgetsContain(widgets, "weatherStation", "temperature");
        assertWidgetsContain(widgets, "weatherStation", "humidity");
        assertWidgetsContain(widgets, "weatherStation", "hpa");
    }

    @Test
    void returnTheCorrectWidgetsGivenTheOrigin() {
        List<Widget> widgets = repo.getWidgetsOf("weatherStation");
        assertThat(widgets.size()).isEqualTo(3);
        assertWidgetsContain(widgets, "weatherStation", "temperature");
        assertWidgetsContain(widgets, "weatherStation", "humidity");
        assertWidgetsContain(widgets, "weatherStation", "hpa");

    }

    @Test
    void returnTheCorrectDataPointsGivenTheOrigin() {
        List<DataPoint> dataPoints = repo.getWidgetData("weatherStation","temperature");
        assertThat(dataPoints.size()).isEqualTo(38);
        assertDataPointsContain(dataPoints, Instant.parse("2022-10-28T22:00:00Z"), new BigDecimal("19.9"));


    }

    private void assertDataPointsContain(List<DataPoint> dataPoints, Instant instant, BigDecimal value) {
        assertThat(dataPoints).contains(new DataPoint(instant, value));
    }

    private static void assertWidgetsContain(List<Widget> widgets, String origin, String name) {
        Widget expectedWidget = new Widget(origin, name);
        assertThat(widgets).contains(expectedWidget);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
        template = null;
        repo = null;
    }
}