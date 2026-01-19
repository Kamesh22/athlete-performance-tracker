package com.example.sports.service;

import com.example.sports.entity.Athlete;
import com.example.sports.enums.Sport;
import com.example.sports.repository.AthleteRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class AthleteService {

    private final AthleteRepository repository;

    public AthleteService(AthleteRepository repository) {
        this.repository = repository;
    }

    public List<Athlete> getAthletesBySport(Sport sport) {
        return repository.findBySport(sport);
    }

    public List<String> getAthleteNamesBySport(Sport sport) {
        return repository.findAthleteFullNamesBySport(sport);
    }

    public List<Athlete> getTop3Athletes(Sport sport) {
        return repository.findTop3BySportOrderByCareerPointsDesc(sport.name());
    }

    public int retireInactiveAthletes(LocalDate cutoffDate) {
        return repository.bulkRetireAthletes(cutoffDate);
    }

    /* Pagination & Sorting */
    public Page<Athlete> getAthletes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /* Top 10 dynamic sorting */
    public Page<Athlete> getTop10Athletes(String sortBy) {
        Sort sort = sortBy.equalsIgnoreCase("age")
                ? Sort.by(Sort.Direction.ASC, "age")
                : Sort.by(Sort.Direction.DESC, "careerPoints");

        Pageable pageable = PageRequest.of(0, 10, sort);
        return repository.findAll(pageable);
    }
}
