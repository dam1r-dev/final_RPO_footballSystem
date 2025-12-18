package com.example.demo.service.Impl;

import com.example.demo.dto.MatchDto;
import com.example.demo.mapper.MatchMapper;
import com.example.demo.model.Match;
import com.example.demo.repository.MatchRepository;
import com.example.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public List<MatchDto> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(matchMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDto getMatchById(Long id) {
        return matchRepository.findById(id)
                .map(matchMapper::toDto)
                .orElse(null);
    }

    @Override
    public MatchDto createMatch(MatchDto matchDto) {
        Match match = matchMapper.toEntity(matchDto);
        return matchMapper.toDto(matchRepository.save(match));
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }
}