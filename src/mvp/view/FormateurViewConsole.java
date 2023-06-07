package mvp.view;

import classesmetiers.Formateur;
import classesmetiers.SessionCours;
import mvp.presenter.FormateurPresenter;
import mvp.presenter.Presenter;

import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import mvp.presenter.SessionCoursPresenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static Utilitaires.Utilitaires.*;

public class FormateurViewConsole implements ViewInterface<Formateur>{
    private Presenter<Formateur> presenter;

    private List<Formateur> ldatas;

    private Scanner sc = new Scanner(System.in);

    private static final Logger logger = LogManager.getLogger(FormateurViewConsole.class);

    protected void rechercher()
    {
        try
        {
            System.out.println("id du formateur :");
            int id = lireInt();
            Formateur rech = null;
            rech = new Formateur(id,"x","x","x");
            presenter.search(rech);
        }catch (Exception e)
        {
            System.out.println("Erreur : "+e);
        }
    }

    protected void modifier()
    {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        Formateur f = ldatas.get(choix-1);
        do
        {
            try
            {
              String mail = modifyIfNotBlank("Mail",f.getMail());
              String nom = modifyIfNotBlank("Nom",f.getNom());
              String prenom = modifyIfNotBlank("Prenom",f.getPrenom());
              f.setMail(mail);
              f.setNom(nom);
              f.setPrenom(prenom);
              break;
            }catch (Exception e)
            {
                System.out.println("Erreur :"+e);
            }
        }while (true);
        presenter.update(f);
        ldatas=presenter.getAll();
        affListe(ldatas);
    }

    protected  void retirer() {
        affListe(ldatas);
        int choix = choixElt(ldatas);
        Formateur formateur = ldatas.get(choix-1);
        presenter.remove(formateur);
        ldatas=presenter.getAll();//rafraichissement
        affListe(ldatas);
    }




    protected void ajouter() {
        System.out.println("Mail :");
        String mail = sc.nextLine();
        System.out.println("Nom :");
        String nom = sc.nextLine();
        System.out.println("Prenom :");
        String prenom = sc.nextLine();
        try
        {
            Formateur formateur = new Formateur.FormateurBuilder()
                    .setMail(mail)
                    .setNom(nom)
                    .setPrenom(prenom)
                    .build();
            if (formateur!=null)
            {
                presenter.add(formateur);
                Formateur form = presenter.read(formateur);
                formateur.setId_formateur(form.getId_formateur());
            }
        }catch (Exception e)
        {
            System.out.println("Erreur : "+e);
        }
        ldatas = presenter.getAll();
        affListe(ldatas);
    }


    public void menu(){
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher tous les formateurs","Ajouter un formateur","Modifier un formateur","Effacer un formateur","Rechercher un formateur","Speciales","Retour"));
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
        List<String> loptions = new ArrayList<>(Arrays.asList("Afficher le nombre total d'heures qu'ont effectué les formateurs pour une session donnée","retour"));
        int choix;
        do {
            affListe(loptions);
            choix=choixElt(loptions);
            switch (choix)
            {
                case 1:afficheHeures();break;
                case 2:
                    System.out.println("fin");
            }

        }while(choix!=2);
    }

    @Override
    public void setPresenter(Presenter<Formateur> presenter) {
        this.presenter = (FormateurPresenter) presenter;
    }





    @Override
    public void setListDatas(List<Formateur> ldatas) {
        this.ldatas = ldatas;
        menu();

    }

    @Override
    public void affMsg(String msg) {
        System.out.println("Information : "+msg);
    }


    @Override
    public Formateur selectionner(List<Formateur> l) {
        affListe(l);
        int choix = choixElt(l);
        Formateur f = l.get(choix-1);

        return f;
    }

    public void afficheHeures()
    {
        int choix;
        SessionCours sess;
        List<SessionCours> lSess = new ArrayList<>();
        lSess = ((FormateurPresenter)presenter).lSessionCours();
        affListe(lSess);
        choix=choixElt(lSess);
        sess=lSess.get(choix-1);
        ((FormateurPresenter)presenter).getTotalFormateurHeures(sess.getId_sessionCours());

    }
}
