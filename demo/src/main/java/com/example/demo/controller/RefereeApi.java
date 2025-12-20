package com.example.demo.controller;

import com.example.demo.dto.RefereeDto;
import com.example.demo.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/referees")
@RequiredArgsConstructor
public class RefereeApi {
    private final RefereeService refereeService;

    @GetMapping
    public List<RefereeDto> getAll() {
        return refereeService.getAllReferees();
    }

    @GetMapping("/{id}")
    public RefereeDto getById(@PathVariable Long id) {
        return refereeService.getRefereeById(id);
    }

    @PostMapping
    public RefereeDto create(@RequestBody RefereeDto refereeDto) {
        return refereeService.createReferee(refereeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        refereeService.deleteReferee(id);
    }
}