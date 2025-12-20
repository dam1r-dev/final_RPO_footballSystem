package com.example.demo.controller;

import com.example.demo.dto.StadiumDto;
import com.example.demo.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/stadiums")
@RequiredArgsConstructor
public class StadiumApi {
    private final StadiumService stadiumService;

    @GetMapping
    public List<StadiumDto> getAll() {
        return stadiumService.getAllStadiums();
    }

    @GetMapping("/{id}")
    public StadiumDto getById(@PathVariable Long id) {
        return stadiumService.getStadiumById(id);
    }

    @PostMapping
    public StadiumDto create(@RequestBody StadiumDto stadiumDto) {
        return stadiumService.createStadium(stadiumDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stadiumService.deleteStadium(id);
    }
}