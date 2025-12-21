package com.example.demo.service;

import com.example.demo.dto.RefereeDto;
import java.util.List;

public interface RefereeService {
    List<RefereeDto> getAllReferees();
    RefereeDto getRefereeById(Long id);
    RefereeDto createReferee(RefereeDto refereeDto);
    void delete(Long id);

    RefereeDto getById(Long id);
}