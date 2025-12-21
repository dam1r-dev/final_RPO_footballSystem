package com.example.demo.service;

import com.example.demo.dto.RefereeDto;
import com.example.demo.model.Referee;
import com.example.demo.repository.RefereeRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class RefereeServiceTest {

    @Autowired
    private RefereeService refereeService;

    @Autowired
    private RefereeRepository refereeRepository;

    @Test
    @Transactional
    void getByIdTest() {
        Referee saved = refereeRepository.save(new Referee(null, "Pierluigi Collina"));
        RefereeDto dto = refereeService.getById(saved.getId());

        Assertions.assertEquals(saved.getName(), dto.getNameDto());
    }

    @Test
    @Transactional
    void deleteTest() {
        Referee saved = refereeRepository.save(new Referee(null, "To Delete"));
        refereeService.delete(saved.getId());

        Assertions.assertThrows(RuntimeException.class, () -> refereeService.getById(saved.getId()));
    }
}