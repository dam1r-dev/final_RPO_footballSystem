package com.example.demo.controller;

import com.example.demo.dto.MatchDto;
import com.example.demo.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/matches")
@RequiredArgsConstructor
public class MatchApi {
    private final MatchService matchService;

    @GetMapping
    public List<MatchDto> getAll() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{id}")
    public MatchDto getById(@PathVariable Long id) {
        return matchService.getMatchById(id);
    }

    @PostMapping
    public MatchDto create(@RequestBody MatchDto matchDto) {
        return matchService.createMatch(matchDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }
}