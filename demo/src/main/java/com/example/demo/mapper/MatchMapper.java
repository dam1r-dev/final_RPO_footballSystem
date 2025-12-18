package com.example.demo.mapper;

import com.example.demo.dto.MatchDto;
import com.example.demo.model.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchMapper {
    private final StadiumMapper stadiumMapper;
    private final RefereeMapper refereeMapper;

    public MatchDto toDto(Match match) {
        if (match == null) return null;
        return new MatchDto(
                match.getId(),
                match.getName(),
                match.getMatchDate(),
                stadiumMapper.toDto(match.getStadium()),
                match.getReferees() != null ? match.getReferees().stream()
                        .map(refereeMapper::toDto)
                        .collect(Collectors.toList()) : null
        );
    }

    public Match toEntity(MatchDto dto) {
        if (dto == null) return null;
        return new Match(
                dto.getId(),
                dto.getNameDto(),
                dto.getMatchDate(),
                stadiumMapper.toEntity(dto.getStadium()),
                dto.getReferees() != null ? dto.getReferees().stream()
                        .map(refereeMapper::toEntity)
                        .collect(Collectors.toList()) : null
        );
    }
}