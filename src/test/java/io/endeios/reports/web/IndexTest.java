package io.endeios.reports.web;

import io.endeios.reports.web.controllers.Index;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Index.class)
public class IndexTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
    }

    @Test
    public void load(){
        assertThat(webApplicationContext).isNotNull();
    }

    @Test
    public void indexHomeHasWidgets() throws Exception {
        ResultActions result = mvc.perform(get("/"));
        System.out.println(result.andReturn().getResponse().getContentAsString());
        result.andExpect(jsonPath("name").isString());
        result.andExpect(status().isOk());

    }

}
