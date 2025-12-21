package com.example.demo.service.Impl;

import com.example.demo.dto.StadiumDto;
import com.example.demo.mapper.StadiumMapper;
import com.example.demo.model.Stadium;
import com.example.demo.repository.StadiumRepository;
import com.example.demo.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StadiumServiceImpl implements StadiumService {
    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    @Override
    public List<StadiumDto> getStadiumAll() {
        return stadiumRepository.findAll().stream()
                .map(stadiumMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StadiumDto getStadiumById(Long id) {
        return stadiumRepository.findById(id)
                .map(stadiumMapper::toDto)
                .orElse(null);
    }

    @Override
    public StadiumDto createStadium(StadiumDto stadiumDto) {
        Stadium stadium = stadiumMapper.toEntity(stadiumDto);
        return stadiumMapper.toDto(stadiumRepository.save(stadium));
    }

    @Override
    public void deleteStadium(Long id) {
        stadiumRepository.deleteById(id);
    }
}