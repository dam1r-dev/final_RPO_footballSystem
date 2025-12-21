package com.example.demo.service;

import com.example.demo.dto.StadiumDto;
import java.util.List;

public interface StadiumService {
    List<StadiumDto> getStadiumAll();
    StadiumDto getStadiumById(Long id);
    StadiumDto createStadium(StadiumDto stadiumDto);
    void deleteStadium(Long id);
}