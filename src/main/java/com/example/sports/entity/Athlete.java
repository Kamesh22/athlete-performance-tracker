package com.example.sports.entity;

import java.time.LocalDate;

import com.example.sports.enums.Sport;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name = "athletes")
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sport sport;

    private Integer age;
    private Double careerPoints;
    private Boolean isActive;
    private LocalDate lastMatchDate;

    // Constructors
    public Athlete() {}

    public Athlete(String firstName, String lastName, Sport sport, Integer age,
                   Double careerPoints, Boolean isActive, LocalDate lastMatchDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.age = age;
        this.careerPoints = careerPoints;
        this.isActive = isActive;
        this.lastMatchDate = lastMatchDate;
    }
}
