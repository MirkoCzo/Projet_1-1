package Utilitaires;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Utilitaires {
    private static Scanner sc = new Scanner(System.in);

    public static LocalDate lectureDate(){
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        return LocalDate.of(a,m,j);
    }


    public static int choixListe(List l){
        affListe(l);
        return choixElt(l);
    }

    public static void affListe(List l){
        int i =1;
        for(Object o :l) {
            System.out.println((i++)+"."+o);
        }
    }

    public static int choixElt(List l){
        int choix;
        do {
            System.out.println("choix :");
            choix = sc.nextInt();
            sc.skip("\n");
        } while(choix <1 || choix > l.size());
        return choix;
    }
    public static String modifyIfNotBlank(String label,String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue= sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }
    public static LocalTime lecTime(){
        String[] hms = sc.nextLine().split(" ");
        int h = Integer.parseInt(hms[0]);
        int m = Integer.parseInt(hms[1]);
        int s = Integer.parseInt(hms[2]);
        return LocalTime.of(h,m,s);
    }

    public static int lireInt(){
        int n=0;
        do{
            try {
                String ns = sc.nextLine();
                n=Integer.parseInt(ns);
                return n;
            }
            catch(NumberFormatException e){
                System.out.println("valeur numérique incorrecte");
            }

        } while(true);
    }
}