package com.example.sports.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.sports.entity.Athlete;
import com.example.sports.enums.Sport;

import jakarta.transaction.Transactional;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {

    // 1. Find all athletes by sport
    List<Athlete> findBySport(Sport sport);

    // 2. Find by last name ignoring case
    List<Athlete> findByLastNameIgnoreCase(String lastName);

    // 3. Active athletes younger than given age
    List<Athlete> findByIsActiveTrueAndAgeLessThan(Integer age);

    // 4. Career points within range
    List<Athlete> findByCareerPointsBetween(Double min, Double max);

    // Retrieve concatenated full names by sport
    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName) " +
           "FROM Athlete a WHERE a.sport = :sport")
    List<String> findAthleteFullNamesBySport(@Param("sport") Sport sport);

    // Top 3 highest scoring athletes per sport
    @Query(value = """
        SELECT * FROM athletes
        WHERE sport = :sport
        ORDER BY career_points DESC
        LIMIT 3
        """, nativeQuery = true)
    List<Athlete> findTop3BySportOrderByCareerPointsDesc(@Param("sport") String sport);

    // Bulk retire athletes not played since a date
    @Modifying
    @Transactional
    @Query("UPDATE Athlete a SET a.isActive = false WHERE a.lastMatchDate < :date")
    int bulkRetireAthletes(@Param("date") LocalDate date);

    Page<Athlete> findAll(Pageable pageable);
}
