package com.example.demo.service;

import com.example.demo.dto.MatchDto;
import java.util.List;

public interface MatchService {
    List<MatchDto> getAllMatches();
    MatchDto getMatchById(Long id);
    MatchDto create(MatchDto matchDto);
    void deleteMatch(Long id);

}