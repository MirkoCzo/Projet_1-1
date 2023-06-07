package mvp.view;

import classesmetiers.Cours;
import classesmetiers.Local;
import classesmetiers.SessionCours;
import mvp.model.SessionCoursSpecial;
import mvp.presenter.Presenter;
import mvp.presenter.SessionCoursPresenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import mvp.presenter.SpecialSessionCoursPresenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static Utilitaires.Utilitaires.*;

public class SessionCoursViewConsole implements ViewInterface<SessionCours> {

    private Presenter<SessionCours> presenter;

    private List<SessionCours> ldatas;

    private Scanner sc = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(SessionCoursPresenter.class);

    protected void rechercher() {
        try
        {
            System.out.println("Id de la Session :");
            int idSession = lireInt();
            SessionCours rech = null;
            rech = new SessionCours(idSession,null,null,0,null,null);
            presenter.search(rech);
        }catch(Exception e)
        {
            System.out.println("Erreur "+e);
        }
    }

    public void menu(){
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher toutes les sessions de cours","Ajouter une session","Modifier une session","Effacer une session","Rechercher une session","Speciales","Retour"));

        int choix;

        do{
            affListe(loptions);
            choix = choixElt(loptions);
            switch(choix){
                case 1 : affListe(ldatas);break;
                case 2 : ajouter();break;
                case 3 : modifier();break;
                case 4 : retirer();break;
                case 5 : rechercher();break;
                case 6 : special();break;
                case 7 :
                    System.out.println("Fin");
            }
        }while(choix!=7);
    }
    public void special()
    {
        List<String> loptions = new ArrayList<>(Arrays.asList("Ajouter une session","retour"));
        int choix;
        do {
            SessionCours sess;
            affListe(loptions);
            choix=choixElt(loptions);
            switch (choix)
            {
                case 1:sess=creerSession();
                    addSession(sess);break;
                case 2:
                    System.out.println("fin");
            }

        }while(choix!=2);
    }

    public void addSession(SessionCours sc)
    {
        int ID_SESS;
        ID_SESS = ((SpecialSessionCoursPresenter)presenter).addSession(sc);
    }





    protected void modifier() {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        SessionCours sc = ldatas.get(choix-1);
        do {
            try
            {
                LocalDate dateDebut = LocalDate.parse(modifyIfNotBlank("Date début", String.valueOf(sc.getDateDebut())));
                LocalDate dateFin = LocalDate.parse(modifyIfNotBlank("date fin", String.valueOf(sc.getDateFin())));
                int nbr = Integer.parseInt(modifyIfNotBlank("Nombre d'inscrits", String.valueOf(sc.getNbreInscrits())));
                sc.setDateDebut(dateDebut);
                sc.setDateFin(dateFin);
                sc.setNbreInscrits(nbr);
                break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }
        }while (true);
        presenter.update(sc);
        ldatas = presenter.getAll();
        affListe(ldatas);

    }

    protected SessionCours creerSession() {
        SessionCours sess;
        System.out.println("Date de début ");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dateDebut = LocalDate.of(a, m, j);
        System.out.println("Date de fin ");
        String[] zer = sc.nextLine().split(" ");
        int z = Integer.parseInt(zer[0]);
        int e = Integer.parseInt(zer[1]);
        int r = Integer.parseInt(zer[2]);
        LocalDate dateFin = LocalDate.of(r, e, z);
        System.out.println("Nombre d'inscrits");
        int nbr = sc.nextInt();
        System.out.println("Cours");
        List<Cours> lCours = ((SessionCoursPresenter) presenter).choixCours();
        affListe(lCours);
        int choixCours = choixElt(lCours);
        Cours c = lCours.get(choixCours - 1);
        System.out.println("Local");
        List<Local> lLocal = ((SessionCoursPresenter) presenter).choixLocal();
        affListe(lLocal);
        int choixLocal = choixElt(lLocal);
        Local l = lLocal.get(choixLocal - 1);
        try {
            sess = new SessionCours.SessionCoursBuilder()
                    .setDateDebut(dateDebut)
                    .setDateFin(dateFin)
                    .setNbreInscrits(nbr)
                    .setCours(c)
                    .setLocal(l)
                    .build();
        }catch (Exception x)
        {
            System.out.println("Erreur "+x);
            sess=null;
        }
        return sess;

    }


    protected void ajouter() {

        System.out.println("Date de début ");
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        LocalDate dateDebut = LocalDate.of(a,m,j);
        System.out.println("Date de fin ");
        String[] zer = sc.nextLine().split(" ");
        int z = Integer.parseInt(zer[0]);
        int e = Integer.parseInt(zer[1]);
        int r = Integer.parseInt(zer[2]);
        LocalDate dateFin = LocalDate.of(r,e,z);
        System.out.println("Nombre d'inscrits");
        int nbr = sc.nextInt();
        System.out.println("Cours");
        List<Cours> lCours = ((SessionCoursPresenter)presenter).choixCours();
        affListe(lCours);
        int choixCours = choixElt(lCours);
        Cours c = lCours.get(choixCours-1);
        System.out.println("Local");
        List<Local> lLocal = ((SessionCoursPresenter)presenter).choixLocal();
        affListe(lLocal);
        int choixLocal = choixElt(lLocal);
        Local l = lLocal.get(choixLocal-1);

        try
        {
            SessionCours sess = new SessionCours.SessionCoursBuilder()
                    .setDateDebut(dateDebut)
                    .setDateFin(dateFin)
                    .setNbreInscrits(nbr)
                    .setCours(c)
                    .setLocal(l)
                    .build();
            if(sess!=null) {
                presenter.add(sess);
                SessionCours session = presenter.read(sess);
                sess.setId_sessionCours(session.getId_sessionCours());
            }
        }catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
        ldatas = presenter.getAll();
        sc.skip("\n");
    }

    protected void retirer()
    {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        SessionCours sess = ldatas.get(choix-1);
        presenter.remove(sess);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }

    @Override
    public void setPresenter(Presenter<SessionCours> presenter) {
        this.presenter = (SessionCoursPresenter) presenter;

    }

    @Override
    public void setListDatas(List<SessionCours> ldatas) {
        this.ldatas = ldatas;
        menu();

    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : " + msg);

    }



    @Override
    public SessionCours selectionner(List<SessionCours> l) {
       affListe(l);
       int choix = choixElt(l);
       SessionCours sess = l.get(choix-1);
       return sess;
    }


}
