package designpatterns.observer;

import classesmetiers.Local;

public class TestOb {
    public static void main(String[] args) {
        SessionCours sessionCours = new SessionCours(1,new Local(1,"F4",26,"hello"),12);
        Local loc = new Local(2,"F6",12,"hello");

        Formateur formateur1 = new Formateur(1, "formateur1@mail.com", "John", "Doe");
        Formateur formateur2 = new Formateur(2, "formateur2@mail.com", "Alice", "Malice");
        System.out.println(sessionCours);
        sessionCours.addObserver(formateur1);
        sessionCours.addObserver(formateur2);
        sessionCours.setLocal(loc);
        sessionCours.setNbreInscrits(6);


    }


}
