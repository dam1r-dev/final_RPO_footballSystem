package com.example.demo.controller;

import com.example.demo.dto.MatchDto;
import com.example.demo.dto.RefereeDto;
import com.example.demo.dto.StadiumDto;
import com.example.demo.service.MatchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = MatchApi.class, excludeAutoConfiguration = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MatchService matchService;

    @Test
    void createMatch_ShouldReturnOk() throws Exception {
        StadiumDto stadiumDto = new StadiumDto();
        stadiumDto.setId(1L);
        stadiumDto.setNameDto("Astana Arena");

        RefereeDto refereeDto = new RefereeDto();
        refereeDto.setId(1L);
        refereeDto.setNameDto("Referee Name");

        MatchDto matchDto = new MatchDto();
        matchDto.setNameDto("Final Match");
        matchDto.setMatchDate(LocalDateTime.of(2025, 12, 22, 12, 0));
        matchDto.setStadium(stadiumDto);
        matchDto.setReferees(List.of(refereeDto));

        when(matchService.create(any(MatchDto.class))).thenReturn(matchDto);

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "nameDto": "Final Match",
                          "matchDate": "2025-12-22T12:00:00",
                          "stadium": { "id": 1, "nameDto": "Astana Arena" },
                          "referees": [{ "id": 1, "nameDto": "Referee Name" }]
                        }
                        """))
                .andDo(print())
                .andExpect(status().isOk());
    }
}