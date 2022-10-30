package io.endeios.reports.web;

import io.endeios.reports.appLogic.WidgetService;
import io.endeios.reports.web.controllers.Index;
import io.endeios.reports.web.dto.Widget;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void load(){
        assertThat(webApplicationContext).isNotNull();
    }

    @Test
    public void indexHomeHasWidgets() throws Exception {
        doReturn(List.of(new Widget("origin","test"))).when(widgetService).getAll();

        ResultActions result = mvc.perform(get("/"));

        System.out.println(result.andReturn().getResponse().getContentAsString());
        result.andExpect(jsonPath("_embedded.widgetList[0].name").isString());
        result.andExpect(jsonPath("_embedded.widgetList[0].name").value("test"));
        result.andExpect(status().isOk());

    }

}
