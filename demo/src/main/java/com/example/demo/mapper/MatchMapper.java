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

        MatchDto dto = new MatchDto();
        dto.setId(match.getId());
        dto.setNameDto(match.getName());
        dto.setMatchDateDto(match.getMatchDate());

        if (match.getStadium() != null) {
            dto.setStadium(stadiumMapper.toDto(match.getStadium()));
        }

        if (match.getReferee() != null) {
            dto.setReferee(refereeMapper.toDto(match.getReferee()));
        }

        return dto;
    }
}