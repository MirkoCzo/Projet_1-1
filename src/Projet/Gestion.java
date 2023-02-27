package Projet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Gestion {
    List<Formateur> listeFormateur = new ArrayList<>();
    List<Cours> listeCours = new ArrayList<>();
    List<SessionCours> listeSessionCours = new ArrayList<>();
    List<Infos> listeInfos = new ArrayList<>();
    List<Local> listeLocal = new ArrayList<>();
    public Gestion()
    {
        menu();
    }

    public void menu()
    {
        int choix;
        Scanner sc = new Scanner(System.in);
        String st = "1.Ajouter un formateur\n2.Ajouter un cours\n3.Ajouter une session de cours\n4.Ajouter un local\n5.Changer le nombre d'inscrit pour une session\n6.Affecter un formateur à une session de cours\n7.Quitter";
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
                case 5: changeInscrits();break;
                case 6: affectationSession();
                case 7:
                    System.out.println("Aurevoir");break;
                default:
                    System.out.println("Mauvais choix.");break;
            }
        }while(choix!=7);
    }
    public void ajoutFormateur()
    {
        Scanner sc = new Scanner(System.in);
        String nom,prenom,mail;
        boolean check;
        Formateur F;
        int id;
        do
        {
            System.out.println("Entrez l'identifiant du formateur");
            id=sc.nextInt();
            check=checkFormateur(id);
            sc.skip("\n");
        }while(!check);
        System.out.println("Entrez le nom du formateur");
        nom=sc.nextLine();
        System.out.println("Entrez le prenom du formateur");
        prenom=sc.nextLine();
        System.out.println("Entrez le mail du formateur");
        mail=sc.nextLine();
        F = new Formateur(id,mail,nom,prenom);
        listeFormateur.add(F);
    }
    public boolean checkFormateur(int id)
    {

        for (Formateur f : listeFormateur)
        {
            if(id==f.getId_formateur())
            {
                System.out.println("Cet identifiant existe déjà");
                return false;
            }
        }
        return true;
    }
    public void ajoutCours()
    {
        String mat;
        int id,heures;
        boolean check;
        Cours c;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("Entrez l'identifiant du cours");
            id=sc.nextInt();
            check=checkCours(id);
            sc.skip("\n");
        }while(!check);
        System.out.println("Entrez le nom de la matière");
        mat=sc.nextLine();
        System.out.println("Entrez le nombre d'heure pour ce cours");
        heures=sc.nextInt();
        c = new Cours(id,mat,heures);
        listeCours.add(c);

    }
    public void ajoutSession()
    {
        Scanner sc = new Scanner(System.in);
        SessionCours Se;
        int id,nbrInscrit;
        LocalDate dateDebut;
        LocalDate dateFin;
        Cours c;
        Local l;
        int jour,mois,annee;
        boolean check;

        do
        {
            System.out.println("Entrez l'id de la session");
            id=sc.nextInt();
            check=checkSession(id);
            sc.skip("\n");
        }while(!check);
        System.out.println("Entrez la date de début : ");
        System.out.println("Entrez l'année");
        annee=sc.nextInt();
        System.out.println("Entrez le mois");
        mois=sc.nextInt();
        System.out.println("Entrez le jour");
        jour=sc.nextInt();
        dateDebut = LocalDate.of(annee,mois,jour);
        System.out.println("Entrez la date de fin : ");
        System.out.println("Entrez l'année");
        annee=sc.nextInt();
        System.out.println("Entrez le mois");
        mois=sc.nextInt();
        System.out.println("Entrez le jour");
        jour=sc.nextInt();
        dateFin = LocalDate.of(annee,mois,jour);
        System.out.println("Entrez le nombre d'inscrit");
        nbrInscrit=sc.nextInt();
        c=choixCours();
        l=choixLocal();
        Se = new SessionCours(id,dateDebut,dateFin,nbrInscrit,c,l);
        listeSessionCours.add(Se);
    }
    public Cours choixCours()
    {
        Scanner sc = new Scanner(System.in);
        int choix,i=1;
        for(Cours c : listeCours)
        {
            System.out.println(i+"."+c.getMatiere());
            i++;
        }
        System.out.println("Votre choix?");
        choix=sc.nextInt();
        return listeCours.get(choix-1);

    }
    //TODO Ajouter des verifications sur les listes.
    public Local choixLocal()
    {
        Scanner sc = new Scanner(System.in);
        int choix,i=1;
        for(Local l : listeLocal)
        {
            System.out.println(i+"."+l.getSigle());
            i++;
        }
        System.out.println("Votre choix?");
        choix=sc.nextInt();
        return listeLocal.get(choix-1);
    }
    public boolean checkSession(int id)
    {
        for(SessionCours sc : listeSessionCours)
        {
            if(sc.getId_sessionCours()==id)
            {
                System.out.println("Id de session déjà existant");
                return false;
            }
        }
        return true;

    }
    public boolean checkCours(int id)
    {

        for (Cours c : listeCours)
        {
            if(id==c.getId())
            {
                System.out.println("Cet identifiant existe déjà");
                return false;
            }
        }
        return true;
    }
    public boolean checkLocal(int id)
    {
        for (Local l : listeLocal)
        {
            if(id==l.getId_local())
            {
                System.out.println("Cet identifiant existe déjà");
                return false;
            }
        }
        return true;
    }
    public void ajoutLocal()
    {
        int id,places;
        String description,sigle;
        Local l;
        Scanner sc = new Scanner(System.in);
        boolean check;

        do {
            System.out.println("Entrez l'id du local");
            id=sc.nextInt();
            check=checkLocal(id);
            sc.skip("\n");
        }while(!check);
        System.out.println("Entrez le sigle du local");
        sigle=sc.nextLine();
        System.out.println("Entrez la description du local");
        description=sc.nextLine();
        System.out.println("Entrez le nombre de places du local");
        places=sc.nextInt();
        l = new Local(id,sigle,places,description);
        listeLocal.add(l);

    }
    public void affectationSession()
    {
        Scanner sc = new Scanner(System.in);
        int i=1;
        int choix;
        SessionCours s;
        Formateur f;
        Infos inf;
        for(SessionCours se : listeSessionCours)
        {
            if(se.getInfosList()==null)
            {
                System.out.println(i+"."+se);
                i++;
            }
        }
        System.out.println("Quelle Session souhaitez-vous affecter?");
        choix=sc.nextInt();
        s=listeSessionCours.get(choix-1);
        f=choixFormateur();
        System.out.println("Nombre d'heures?");
        choix=sc.nextInt();
        inf = new Infos(choix,f,s);
        s.getInfosList().add(inf);
    }
    public Formateur choixFormateur()
    {
        Scanner sc = new Scanner(System.in);
        int i=1;
        int choix;
        for(Formateur f : listeFormateur)
        {
            System.out.println(i+"."+f.getNom()+" "+f.getPrenom());
        }
        System.out.println("Votre choix?");
        choix=sc.nextInt();
        return listeFormateur.get(choix-1);

    }

    public void changeInscrits()
    {
        Scanner sc = new Scanner(System.in);
        int choix;
        int i=1;
        SessionCours s;
        for (SessionCours Se : listeSessionCours)
        {
            System.out.println(i+"."+Se);
        }
        System.out.println("Votre choix?");
        choix=sc.nextInt();
        s=listeSessionCours.get(choix-1);
        System.out.println("Nouveau nombre d'inscrit?");
        choix=sc.nextInt();
        s.setNbreInscrits(choix);



    }
}

