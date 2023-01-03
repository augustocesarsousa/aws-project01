package br.com.acsousa.aws_project01.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerTest {

    @Autowired
	private MockMvc mockMvc;
    
    @Test
    public void shouldReturnOkOnTestController() throws Exception {
        mockMvc.perform(get("/api/test/dog/Sushi"))
        .andExpect(status().isOk());
    }
}
