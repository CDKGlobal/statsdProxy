package com.cdk.tools.statsdproxy.web;

import com.cdk.tools.statsdproxy.App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { App.class } )
public class MetricsControllerTest {

    MockMvc mockMvc;

    @Resource
    private MetricsController controller;

    @Before
    public void init() {
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    public void testMissingArgumentsThrowsError() throws Exception {
        this.mockMvc.perform(
                get("/submit")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(400));
    }

    @Test
    public void testValidArguments() throws Exception {
        this.mockMvc.perform(
                get("/submit?m=test&v=1&t=s")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(200));
    }

}