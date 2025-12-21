package com.example.demo.mapper;

import com.example.demo.dto.MatchDto;
import com.example.demo.model.Match;
import com.example.demo.model.Stadium;
import com.example.demo.model.Referee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class MatchMapperTest {

    @Autowired
    private MatchMapper matchMapper;

    @Test
    void convertEntityToDto() {
        Stadium stadium = new Stadium(1L, "Camp Nou");
        Referee ref1 = new Referee(1L, "Howard Webb");
        Referee ref2 = new Referee(2L, "Mark Clattenburg");

        Match match = new Match();
        match.setId(100L);
        match.setName("El Clasico");
        match.setMatchDate(LocalDateTime.now());
        match.setStadium(stadium);
        match.setReferees(List.of(ref1, ref2));

        MatchDto dto = matchMapper.toDto(match);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(match.getId(), dto.getId());
        Assertions.assertEquals(match.getName(), dto.getNameDto()); // Проверь название поля в своем DTO!

        Assertions.assertNotNull(dto.getStadium());
        Assertions.assertEquals("Camp Nou", dto.getStadium().getName());

        Assertions.assertNotNull(dto.getReferees());
        Assertions.assertEquals(2, dto.getReferees().size());
        Assertions.assertEquals("Howard Webb", dto.getReferees().get(0).getName());
    }
}