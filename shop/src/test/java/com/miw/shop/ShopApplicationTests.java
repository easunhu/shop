package com.miw.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShopApplicationTests {

    @Autowired
    MockMvc mvc;

    @Test
    void HelloWorldTest() throws Exception {
        this.mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World!")));
    }

    @Test
    void HelloNameTest() throws Exception {
        this.mvc.perform(get("/hello").param("myName", "Ethan"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Ethan!")));
    }
}
