package com.example.demo.service;

import com.example.demo.dto.RefereeDto;
import com.example.demo.mapper.RefereeMapper;
import com.example.demo.model.Referee;
import com.example.demo.repository.RefereeRepository;
import com.example.demo.service.Impl.RefereeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RefereeServiceTest {

    @Mock
    private RefereeRepository refereeRepository;

    @Mock
    private RefereeMapper refereeMapper;

    @InjectMocks
    private RefereeServiceImpl refereeService;

    @Test
    void getByIdTest() {
        Referee referee = new Referee(1L, "Pierluigi Collina");
        RefereeDto dto = new RefereeDto();
        dto.setId(1L);
        dto.setNameDto("Pierluigi Collina");

        when(refereeRepository.findById(1L)).thenReturn(Optional.of(referee));
        when(refereeMapper.toDto(referee)).thenReturn(dto);

        RefereeDto result = refereeService.getRefereeById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Pierluigi Collina", result.getNameDto());
    }
    @Test
    void deleteTest() {
        Long id = 1L;
        refereeService.delete(id);
        verify(refereeRepository, times(1)).deleteById(id);
    }
}