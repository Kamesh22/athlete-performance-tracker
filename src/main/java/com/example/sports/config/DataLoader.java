package com.example.sports.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.sports.entity.Athlete;
import com.example.sports.enums.Sport;
import com.example.sports.repository.AthleteRepository;
import com.example.sports.service.AthleteService;

@Configuration
public class DataLoader {

    @Bean
    @Order(1)
    CommandLineRunner loadAthletes(AthleteRepository repo) {
        return args -> {
            repo.save(new Athlete("Virat", "Kohli", Sport.CRICKET, 35, 1200.0, true, LocalDate.now().minusDays(10)));
            repo.save(new Athlete("Lionel", "Messi", Sport.FOOTBALL, 36, 1500.0, true, LocalDate.now().minusDays(5)));
            repo.save(new Athlete("Rafael", "Nadal", Sport.TENNIS, 37, 1400.0, true, LocalDate.now().minusMonths(3)));
            repo.save(new Athlete("Roger", "Federer", Sport.TENNIS, 42, 1600.0, false, LocalDate.now().minusYears(2)));
            repo.save(new Athlete("Cristiano", "Ronaldo", Sport.FOOTBALL, 39, 1550.0, true, LocalDate.now().minusDays(8)));
            repo.save(new Athlete("MS", "Dhoni", Sport.CRICKET, 42, 1100.0, false, LocalDate.now().minusYears(1)));
            repo.save(new Athlete("PV", "Sindhu", Sport.BADMINTON, 28, 900.0, true, LocalDate.now().minusDays(20)));
            repo.save(new Athlete("Neeraj", "Chopra", Sport.ATHLETICS, 26, 800.0, true, LocalDate.now().minusDays(30)));
            repo.save(new Athlete("Kylian", "Mbappe", Sport.FOOTBALL, 25, 1300.0, true, LocalDate.now().minusDays(3)));
            repo.save(new Athlete("Rohit", "Sharma", Sport.CRICKET, 36, 1000.0, true, LocalDate.now().minusDays(15)));
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner demo(AthleteService service) {
        return args -> {

            System.out.println("\n--- Find by Sport (Query Method) ---");
            service.getAthletesBySport(Sport.CRICKET)
                .forEach(a -> System.out.println(a.getFirstName()));

            System.out.println("\n--- JPQL Full Names ---");
            service.getAthleteNamesBySport(Sport.FOOTBALL)
                .forEach(System.out::println);

            System.out.println("\n--- Native Query (Top 3) ---");
            service.getTop3Athletes(Sport.TENNIS)
                .forEach(a ->
                    System.out.println(a.getFirstName() + " : " + a.getCareerPoints())
                );

            System.out.println("\n--- Pagination + Sorting ---");
            service.getTop10Athletes("points")
                .forEach(a ->
                    System.out.println(a.getFirstName() + " : " + a.getCareerPoints())
                );

            System.out.println("\n--- Bulk Update (Modifying Query) ---");
            int retired = service.retireInactiveAthletes(
                    java.time.LocalDate.now().minusMonths(12));
            System.out.println("Retired Athletes: " + retired);
        };
    }

}
