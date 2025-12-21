package com.example.demo.mapper;

import com.example.demo.dto.RefereeDto;
import com.example.demo.model.Referee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class RefereeMapperTest {

    @Autowired
    private RefereeMapper refereeMapper;

    @Test
    void convertEntityToDto() {
        Referee referee = new Referee(1L, "Pierluigi Collina");

        RefereeDto dto = refereeMapper.toDto(referee);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(referee.getId(), dto.getId());
        Assertions.assertEquals(referee.getName(), dto.getNameDto());
    }

    @Test
    void convertEntityListToDtoList() {
        List<Referee> referees = List.of(
                new Referee(1L, "Referee 1"),
                new Referee(2L, "Referee 2")
        );

        List<RefereeDto> dtos = refereeMapper.toDtoList(referees);

        Assertions.assertNotNull(dtos);
        Assertions.assertEquals(2, dtos.size());
        Assertions.assertEquals(referees.get(0).getName(), dtos.get(0).getNameDto());
    }
}