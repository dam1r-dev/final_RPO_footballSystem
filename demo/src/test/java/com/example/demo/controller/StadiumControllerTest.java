package com.example.demo.controller;

import com.example.demo.dto.StadiumDto;
import com.example.demo.service.StadiumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StadiumController.class)
public class StadiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StadiumService stadiumService;

    @Test
    void getAllStadiums_ShouldReturnOk() throws Exception {
        StadiumDto stadiumDto = new StadiumDto();
        stadiumDto.setId(1L);
        stadiumDto.setName("Astana Arena");

        when(stadiumService.getStadiumAll()).thenReturn(List.of(stadiumDto));

        mockMvc.perform(get("/api/stadiums"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Astana Arena"));
    }
}