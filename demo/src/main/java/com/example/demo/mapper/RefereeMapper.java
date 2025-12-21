package com.example.demo.mapper;

import com.example.demo.dto.RefereeDto;
import com.example.demo.model.Referee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RefereeMapper {
    public RefereeDto toDto(Referee referee) {
        if (referee == null) return null;
        return new RefereeDto(referee.getId(), referee.getName());
    }

    public Referee toEntity(RefereeDto dto) {
        if (dto == null) return null;
        return new Referee(dto.getId(), dto.getNameDto());
    }

    public List<RefereeDto> toDtoList(List<Referee> referees) {
        if (referees == null) return null;
        return referees.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}