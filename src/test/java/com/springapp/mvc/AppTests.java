package com.springapp.mvc;

import com.springapp.config.DataSourceConfig;
import com.springapp.config.HibernateConfig;
import com.springapp.config.Initializer;
import com.springapp.config.WebAppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Initializer.class,
        WebAppConfig.class, HibernateConfig.class, DataSourceConfig.class})
public class AppTests {
    private MockMvc mockMvc;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void simple() throws Exception {
        MvcResult mvcResult1 = mockMvc.perform(get("/messages"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        MvcResult mvcResult2 = mockMvc.perform(get("/messages"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        mockMvc.perform(asyncDispatch(mvcResult1))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(asyncDispatch(mvcResult2))
                .andDo(print())
                .andExpect(status().isOk());


        mvcResult1 = mockMvc.perform(get("/messages"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        mvcResult2 = mockMvc.perform(get("/messages"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
        mockMvc.perform(asyncDispatch(mvcResult1))
                .andDo(print())
                .andExpect(status().isOk());
        mockMvc.perform(asyncDispatch(mvcResult2))
                .andDo(print())
                .andExpect(status().isOk());


    }
}
