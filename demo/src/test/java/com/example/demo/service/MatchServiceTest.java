package com.example.demo.service;

import com.example.demo.dto.MatchDto;
import com.example.demo.mapper.MatchMapper;
import com.example.demo.model.Match;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.Impl.MatchServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceTest {

    @Mock
    private MatchRepository matchRepository;
    @Mock
    private MatchMapper matchMapper;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void createMatchTest() {
        MatchDto inputDto = new MatchDto();
        inputDto.setNameDto("Man Utd vs Liverpool");

        Match matchEntity = new Match();
        matchEntity.setId(1L);
        matchEntity.setName("Man Utd vs Liverpool");

        MatchDto outputDto = new MatchDto();
        outputDto.setId(1L);
        outputDto.setNameDto("Man Utd vs Liverpool");

        lenient().when(matchMapper.toEntity(any(MatchDto.class))).thenReturn(matchEntity);
        lenient().when(matchRepository.save(any(Match.class))).thenReturn(matchEntity);
        lenient().when(matchMapper.toDto(any(Match.class))).thenReturn(outputDto);

        MatchDto result = matchService.create(inputDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Man Utd vs Liverpool", result.getNameDto());
    }
}