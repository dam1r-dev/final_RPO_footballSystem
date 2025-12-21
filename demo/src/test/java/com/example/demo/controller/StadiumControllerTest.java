package com.example.demo.controller;

import com.example.demo.dto.StadiumDto;
import com.example.demo.service.StadiumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = StadiumApi.class, excludeAutoConfiguration = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class StadiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StadiumService stadiumService;

    @Test
    void getAll_ShouldReturnList() throws Exception {
        StadiumDto dto = new StadiumDto(1L, "Old Trafford");

        when(stadiumService.getStadiumAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/api/stadiums"))
                .andExpect(status().isOk());
    }

    @Test
    void create_ShouldReturnSavedStadium() throws Exception {
        StadiumDto dto = new StadiumDto(1L, "Camp Nou");

        when(stadiumService.createStadium(any(StadiumDto.class))).thenReturn(dto);

        mockMvc.perform(post("/api/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nameDto\": \"Camp Nou\"}"))
                .andExpect(status().isOk());
    }
}