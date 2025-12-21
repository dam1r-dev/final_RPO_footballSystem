package com.example.demo.service;

import com.example.demo.dto.StadiumDto;
import com.example.demo.model.Stadium;
import com.example.demo.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class StadiumServiceTest {

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Test
    @Transactional
    void getAllTest() {
        stadiumRepository.save(new Stadium(null, "Astana Arena"));
        stadiumRepository.save(new Stadium(null, "Wembley"));

        List<StadiumDto> result = stadiumService.getStadiumAll();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.size() >= 2);
    }

    @Test
    @Transactional
    void createTest() {
        StadiumDto input = new StadiumDto(null, "Camp Nou");
        StadiumDto created = stadiumService.createStadium(input);

        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Camp Nou", created.getName());
    }
}