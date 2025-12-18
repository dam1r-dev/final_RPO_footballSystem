package com.example.demo.service.Impl;

import com.example.demo.dto.RefereeDto;
import com.example.demo.mapper.RefereeMapper;
import com.example.demo.model.Referee;
import com.example.demo.repository.RefereeRepository;
import com.example.demo.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {
    private final RefereeRepository refereeRepository;
    private final RefereeMapper refereeMapper;

    @Override
    public List<RefereeDto> getAllReferees() {
        return refereeRepository.findAll().stream()
                .map(refereeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RefereeDto getRefereeById(Long id) {
        return refereeRepository.findById(id)
                .map(refereeMapper::toDto)
                .orElse(null);
    }

    @Override
    public RefereeDto createReferee(RefereeDto refereeDto) {
        Referee referee = refereeMapper.toEntity(refereeDto);
        return refereeMapper.toDto(refereeRepository.save(referee));
    }

    @Override
    public void deleteReferee(Long id) {
        refereeRepository.deleteById(id);
    }
}