package com.example.demo.controller;

import com.example.demo.dto.RefereeDto;
import com.example.demo.service.RefereeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(value = RefereeApi.class, excludeAutoConfiguration = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class RefereeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RefereeService refereeService;

    @Test
    void getAllReferees_ShouldReturnOk() throws Exception {
        RefereeDto refereeDto = new RefereeDto();
        refereeDto.setId(1L);
        refereeDto.setNameDto("Pierluigi Collina");

        when(refereeService.getAllReferees()).thenReturn(List.of(refereeDto));

        mockMvc.perform(get("/api/referees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nameDto").value("Pierluigi Collina"));
    }
}