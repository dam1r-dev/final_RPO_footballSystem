package com.example.demo.controller;

import com.example.demo.model.Referee;
import com.example.demo.service.RefereeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RefereeController.class)
public class RefereeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RefereeService refereeService;

    @Test
    void getRefereeById_ShouldReturnReferee() throws Exception {
        when(refereeService.getRefereeById(1L)).thenReturn(new Referee(1L, "Collina"));

        mockMvc.perform(get("/api/referees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Collina"));
    }
}