package com.example.demo.service;

import com.example.demo.dto.StadiumDto;
import com.example.demo.mapper.StadiumMapper;
import com.example.demo.model.Stadium;
import com.example.demo.repository.StadiumRepository;
import com.example.demo.service.Impl.StadiumServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StadiumServiceTest {

    @Mock
    private StadiumRepository stadiumRepository;

    @Mock
    private StadiumMapper stadiumMapper;

    @InjectMocks
    private StadiumServiceImpl stadiumService;

    @Test
    void getById_ShouldReturnDto_WhenStadiumExists() {
        Stadium stadium = new Stadium();
        stadium.setId(10L);
        stadium.setName("Luzhniki");

        StadiumDto dto = new StadiumDto();
        dto.setId(10L);
        dto.setNameDto("Luzhniki");

        when(stadiumRepository.findById(10L)).thenReturn(Optional.of(stadium));
        when(stadiumMapper.toDto(stadium)).thenReturn(dto);

        StadiumDto result = stadiumService.getStadiumById(10L);

        assertNotNull(result);
        assertEquals("Luzhniki", result.getNameDto());
        verify(stadiumRepository, times(1)).findById(10L);
    }
}