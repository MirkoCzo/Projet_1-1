package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Gestion {
    List<Formateur> listeFormateur = new ArrayList<>();
    List<Cours> listeCours = new ArrayList<>();
    List<SessionCours> listeSessionCours = new ArrayList<>();
    List<Infos> listeInfos = new ArrayList<>();
    List<Local> listeLocal = new ArrayList<>();
    public void menu()
    {
        int choix;
        Scanner sc = new Scanner(System.in);
        String st = "";
        do
        {
            System.out.println(st);
            System.out.println("Quel choix?");
            choix=sc.nextInt();
            switch (choix)
            {
                case 1: ajoutFormateur();break;
                case 2: ajoutCours();break;
                case 3: ajoutSession();break;
                case 4: ajoutLocal();break;
                case 5: affectationSession();break;
                case 6: changeInscrits();break;
                    System.out.println("Aurevoir");break;
                default:
                    System.out.println("Mauvais choix.");break;
            }
        }while(choix!=4);
    }
}

