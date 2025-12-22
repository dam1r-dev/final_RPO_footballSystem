package com.example.demo.mapper;

import com.example.demo.dto.StadiumDto;
import com.example.demo.model.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public StadiumDto toDto(Stadium stadium) {
        if (stadium == null) return null;
        StadiumDto dto = new StadiumDto();
        dto.setId(stadium.getId());
        dto.setNameDto(stadium.getName());
        dto.setLocationDto(stadium.getLocation());
        return dto;
    }

    public Stadium toEntity(StadiumDto dto) {
        if (dto == null) return null;
        Stadium stadium = new Stadium();
        stadium.setId(dto.getId());
        stadium.setName(dto.getNameDto());
        stadium.setLocation(dto.getLocationDto());
        return stadium;
    }
}