package com.example.demo.service;

import com.example.demo.dto.MatchDto;
import com.example.demo.model.Match;
import com.example.demo.model.Stadium;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("test")
@SpringBootTest
public class MatchServiceTest {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    @Transactional
    void createMatchTest() {
        Stadium stadium = stadiumRepository.save(new Stadium(null, "Old Trafford"));

        MatchDto input = new MatchDto();
        input.setNameDto("Man Utd vs Liverpool");
        input.setId(stadium.getId());

        MatchDto result = matchService.create(input);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals("Man Utd vs Liverpool", result.getNameDto());
    }
}