package com.example.demo.mapper;

import com.example.demo.dto.StadiumDto;
import com.example.demo.model.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {
    public StadiumDto toDto(Stadium stadium) {
        if (stadium == null) return null;
        return new StadiumDto(stadium.getId(), stadium.getName());
    }

    public Stadium toEntity(StadiumDto dto) {
        if (dto == null) return null;
        return new Stadium(dto.getId(), dto.getNameDto());
    }
}