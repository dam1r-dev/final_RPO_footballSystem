package com.example.demo.service.Impl;

import com.example.demo.dto.MatchDto;
import com.example.demo.dto.RefereeDto;
import com.example.demo.dto.StadiumDto;
import com.example.demo.model.Match;
import com.example.demo.model.Referee;
import com.example.demo.model.Stadium;
import com.example.demo.repository.MatchRepository;
import com.example.demo.repository.RefereeRepository;
import com.example.demo.repository.StadiumRepository;
import com.example.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;
    private final StadiumRepository stadiumRepository;
    private final RefereeRepository refereeRepository;

    @Override
    public List<MatchDto> getAllMatches() {
        return matchRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MatchDto getMatchById(Long id) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match not found"));
        return mapToDto(match);
    }

    @Override
    @Transactional
    public MatchDto createMatch(MatchDto matchDto) {
        Match match = new Match();
        match.setName(matchDto.getNameDto());
        match.setMatchDate(matchDto.getMatchDate());

        if (matchDto.getStadium() != null && matchDto.getStadium().getId() != null) {
            Stadium stadium = stadiumRepository.findById(matchDto.getStadium().getId())
                    .orElseThrow(() -> new RuntimeException("Stadium not found"));
            match.setStadium(stadium);
        }

        if (matchDto.getReferees() != null) {
            List<Long> ids = matchDto.getReferees().stream()
                    .map(RefereeDto::getId)
                    .collect(Collectors.toList());
            List<Referee> referees = refereeRepository.findAllById(ids);
            match.setReferees(referees);
        }

        Match savedMatch = matchRepository.save(match);
        return mapToDto(savedMatch);
    }

    @Override
    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    private MatchDto mapToDto(Match match) {
        MatchDto dto = new MatchDto();
        dto.setId(match.getId());
        dto.setNameDto(match.getName());
        dto.setMatchDate(match.getMatchDate());

        if (match.getStadium() != null) {
            dto.setStadium(new StadiumDto(match.getStadium().getId(), match.getStadium().getName()));
        }

        if (match.getReferees() != null) {
            dto.setReferees(match.getReferees().stream()
                    .map(r -> new RefereeDto(r.getId(), r.getName()))
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}