package io.endeios.reports.web;

import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.appLogic.exceptions.NoSuchOriginException;
import io.endeios.reports.appLogic.exceptions.NoSuchWidgetException;
import io.endeios.reports.web.controllers.Index;
import io.endeios.reports.web.dto.DataPoint;
import io.endeios.reports.web.dto.Widget;
import io.endeios.reports.web.dto.WidgetData;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = Index.class)
public class IndexTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private WidgetService widgetService;

    @Before
    public void setup() {
    }

    @Test
    public void load() {
        assertThat(webApplicationContext).isNotNull();
    }

    @Test
    public void indexHomeHasWidgets() throws Exception {
        doReturn(List.of(new Widget("origin", "test"))).when(widgetService).getAll();

        ResultActions result = mvc.perform(get("/"));

        System.out.println(result.andReturn().getResponse().getContentAsString());
        result.andExpect(jsonPath("_embedded.widgetList[0].name").isString());
        result.andExpect(jsonPath("_embedded.widgetList[0].name").value("test"));
        result.andExpect(jsonPath("_embedded.widgetList[0]._links.self.href").value("http://localhost/origin/test"));
        result.andExpect(status().isOk());

    }

    @Test
    void returnTheListOfValueWidgetsOfASource() throws Exception {
        doReturn(List.of(
                new Widget("origin", "test1"),
                new Widget("origin", "test2"),
                new Widget("origin", "test3"))
        ).when(widgetService).getWidgetsOf("origin");

        ResultActions result = mvc.perform(get("/origin/"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("_embedded.widgetList").isArray());
        result.andExpect(jsonPath("_embedded.widgetList[0]._links.self.href").value("http://localhost/origin/test1"));
        result.andExpect(jsonPath("_embedded.widgetList[1]._links.self.href").value("http://localhost/origin/test2"));
        result.andExpect(jsonPath("_embedded.widgetList[2]._links.self.href").value("http://localhost/origin/test3"));
    }

    @Test
    void returnIErrWhenNoWidgetsOfASource() throws Exception {
        doThrow(new NoSuchOriginException("origin"))
        .when(widgetService).getWidgetsOf("origin");

        ResultActions result = mvc.perform(get("/origin/"));
        result.andExpect(status().isInternalServerError());
        assertThat(result.andReturn().getResponse().getContentAsString()).contains("There is not an origin called origin");
    }

    @Test
    void returnLinkToWidgetsWhenNoWidgetsOfASource() throws Exception {
        doThrow(new NoSuchOriginException("origin"))
                .when(widgetService).getWidgetsOf("origin");

        ResultActions result = mvc.perform(get("/origin/"));
        result.andExpect(status().isInternalServerError());
        result.andExpect(jsonPath("_links.index.href").value("http://localhost/"));
        assertThat(result.andReturn().getResponse().getContentAsString()).contains("There is not an origin called origin");
    }

    @Test
    void returnTheListOfAcquisitionsGivenTheCorrectPath() throws Exception {
        List<DataPoint> datapoints = new ArrayList<>();
        datapoints.add(new DataPoint(Instant.parse("2022-10-31T15:00:00.00Z"), BigDecimal.valueOf(21.0) ));

        doReturn(
                new WidgetData("origin", "test1", datapoints)
        ).when(widgetService).getWidgetData("origin","test1");
        ResultActions result = mvc.perform(get("/origin/test1"));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("name").value("test1"));
        result.andExpect(jsonPath("origin").value("origin"));
        result.andExpect(jsonPath("points[0].instant").value("2022-10-31T15:00:00Z"));
        result.andExpect(jsonPath("points[0].value").value(21.0));
        result.andExpect(jsonPath("_links.self.href").value("http://localhost/origin/test1"));
        result.andExpect(jsonPath("_links.index.href").value("http://localhost/"));
        System.out.println(result.andReturn().getResponse().getContentAsString());

    }
    @Test
    void returnIErrWhenNoSuchWidget() throws Exception {
        doThrow(new NoSuchWidgetException("origin","name"))
                .when(widgetService).getWidgetData("origin", "name");

        ResultActions result = mvc.perform(get("/origin/name"));
        result.andExpect(status().isInternalServerError());
        assertThat(result.andReturn().getResponse().getContentAsString()).contains("Could not find a widget in origin/name");
    }

    @Test
    void returnIndexWhenNoSuchWidget() throws Exception {
        doThrow(new NoSuchWidgetException("origin","name"))
                .when(widgetService).getWidgetData("origin", "name");

        ResultActions result = mvc.perform(get("/origin/name"));
        result.andExpect(status().isInternalServerError());
        result.andExpect(jsonPath("_links.index.href").value("http://localhost/"));
        assertThat(result.andReturn().getResponse().getContentAsString()).contains("Could not find a widget in origin/name");
    }
}

