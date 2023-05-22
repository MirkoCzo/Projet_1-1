package designpatterns.builder;

import classesmetiers.Local;

import java.time.LocalDate;

public class TestBuilder {
    public static void main(String[] args) {
        try {
            Cours cours = new Cours.CoursBuilder()
                    .setIdcours(1)
                    .setMatiere("Mathématiques")
                    .setHeures(20)
                    .build();

            Local local = new Local(1,"F4",24,"Local info");

            SessionCours sessionCours = new SessionCours.SessionCoursBuilder()
                    .setIdSessionCours(1)
                    .setDateDebut(LocalDate.of(2022, 6, 1))
                    .setDateFin(LocalDate.of(2023, 6, 30))
                    .setNbreInscrits(15)
                    .setCours(cours)
                    .setLocal(local)
                    .build();

            System.out.println(sessionCours);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
