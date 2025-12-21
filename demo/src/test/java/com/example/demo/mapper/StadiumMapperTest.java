package com.example.demo.mapper;

import com.example.demo.dto.StadiumDto;
import com.example.demo.model.Stadium;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class StadiumMapperTest {

    @Autowired
    private StadiumMapper stadiumMapper;

    @Test
    void convertEntityToDto() {
        Stadium stadium = new Stadium(1L, "Astana Arena");

        StadiumDto dto = stadiumMapper.toDto(stadium);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(stadium.getId(), dto.getId());
        Assertions.assertEquals(stadium.getName(), dto.getName());
    }

    @Test
    void convertDtoToEntity() {
        StadiumDto dto = new StadiumDto(2L, "Almaty Central");

        Stadium stadium = stadiumMapper.toEntity(dto);

        Assertions.assertNotNull(stadium);
        Assertions.assertEquals(dto.getId(), stadium.getId());
        Assertions.assertEquals(dto.getName(), stadium.getName());
    }
}